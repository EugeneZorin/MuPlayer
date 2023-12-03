package com.example.presentation.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.domain.repository.smusic.MusicSwitchPres
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.presentation.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PlayerService : Service() {

    // Getting data from the database
    @Inject
    lateinit var playerStatePres: PlayerStatePres

    // Track changes on the next
    @Inject
    lateinit var musicSwitchContract: MusicSwitchPres

    private lateinit var player: ExoPlayer
    private lateinit var mediaItem: MediaItem
    private lateinit var nameMusic: String

    private var isPlaying: Boolean = true

    override fun onCreate() {
        super.onCreate()
        // Initializing the ExoPlayer
        player = ExoPlayer.Builder(this).build()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        CoroutineScope(Dispatchers.Main).launch {
            handlePlaybackAction(intent?.action)
            updateMediaItem()
            handlePausePlayAction(intent?.action)
            startForeground(ONE, notification())
        }

        return START_STICKY
    }

    private suspend fun handlePlaybackAction(action: String?) {
        when (action) {
            ACTION_NEXT -> {
                musicSwitchContract.nextMusic()
                isPlaying = true
            }

            ACTION_BACK -> {
                musicSwitchContract.backMusic()
                isPlaying = true
            }
        }
    }

    private suspend fun updateMediaItem() {
        // Data is taken from the database to display on the screen and play music
        mediaItem = MediaItem.fromUri(playerStatePres.getData().idMusic)
        nameMusic = playerStatePres.getData().nameMusic

        // Initializing the ExoPlayer
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    private fun handlePausePlayAction(action: String?) {
        when (action) {
            ACTION_PAUSE_PLAY -> {
                if (isPlaying) {
                    isPlaying = false
                    player.pause()
                } else {
                    isPlaying = true
                    player.play()
                }
            }
        }
    }
    
    override fun onTaskRemoved(rootIntent: Intent?) {
        player.stop()
        stopSelf()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private fun createPendingIntent(action: String, requestCode: Int): PendingIntent {
        val intent = Intent(this, PlayerService::class.java).apply {
            this.action = action
        }
        return PendingIntent.getService(
            this,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun notification(): Notification {

        // Implementation of switching music tracks and setting pause or play
        val pausePlayPendingIntent = createPendingIntent(ACTION_PAUSE_PLAY, ONE)
        val nextPendingIntent = createPendingIntent(ACTION_NEXT, TWO)
        val backPendingIntent = createPendingIntent(ACTION_BACK, THREE)

        // NotificationManager
        createNotificationChannel()

        // Changes to the pause and play icons
        val iconResId = if (isPlaying) R.drawable.baseline_pause else R.drawable.baseline_play

        // Notification
        return NotificationCompat.Builder(this, ID)
            .setColor(1000)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(1)
            )
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.baseline_library_music_24)
            .addAction(R.drawable.baseline_skip_previous_24, NEXT, backPendingIntent)
            .addAction(iconResId, SWITCH, pausePlayPendingIntent)
            .addAction(R.drawable.baseline_skip_next, BACK, nextPendingIntent)
            .setContentTitle(nameMusic)
            .build()

    }

    // NotificationManager
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = NAME
            val importance = NotificationManager.IMPORTANCE_MIN
            val channel = NotificationChannel(ID, name, importance)
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {

        // Switch player states
        const val ACTION_PAUSE_PLAY = "ACTION_PAUSE_PLAY"
        const val ACTION_NEXT = "ACTION_NEXT"
        const val ACTION_BACK = "ACTION_BACK"

        // NotificationManager
        const val NAME = "music"

        // id for NotificationCompat
        const val ID = "CHANNEL_ID"

        // Name for NotificationCompat
        const val NEXT = "next"
        const val SWITCH = "switch"
        const val BACK = "back"

        // id for PendingIntent
        const val ONE = 1
        const val TWO = 2
        const val THREE = 3
    }


}