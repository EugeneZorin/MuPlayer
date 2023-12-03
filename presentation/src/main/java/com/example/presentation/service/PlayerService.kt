package com.example.presentation.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
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
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class PlayerService : Service()  {

    // Getting data from the database
    @Inject
    lateinit var playerStatePres: PlayerStatePres

    // Track changes on the next
    @Inject
    lateinit var musicSwitchContract: MusicSwitchPres

    private lateinit var player: ExoPlayer
    private lateinit var mediaItem: MediaItem
    private lateinit var nameMusic: String

    override fun onCreate() {
        super.onCreate()
        // Initializing the ExoPlayer
        player = ExoPlayer.Builder(this).build()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        CoroutineScope(Dispatchers.Main).launch {

            // Switches to the next track in the list
            when(intent?.action){
                ACTION_NEXT -> {
                    musicSwitchContract.nextMusic()
                }
                ACTION_BACK -> {
                    musicSwitchContract.backMusic()
                }
            }

            // Data is taken from the database to display on the screen and play music
            mediaItem = MediaItem.fromUri(playerStatePres.getData().idMusic)
            nameMusic = playerStatePres.getData().nameMusic

            // Initializing the ExoPlayer
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()

            // Initializing the notification
            val notification = notification()
            startForeground(1, notification)
        }

        CoroutineScope(Dispatchers.Main).launch {
            when(intent?.action){
                ACTION_PAUSE -> {
                    player.pause()
                }
                ACTION_PLAY -> {
                    player.play()
                }
            }
        }

        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        player.stop()
        stopSelf()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    private fun notification(): Notification {

        // Implementation of switching music tracks and setting pause or play
         val playIntent = Intent(this, PlayerService::class.java).apply {
            action = ACTION_PLAY
        }

        val playPendingIntent = PendingIntent.getService(
            this, 0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val pauseIntent = Intent(this, PlayerService::class.java).apply {
            action = ACTION_PAUSE
        }

        val pausePendingIntent = PendingIntent.getService(
            this, 1, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val nextIntent = Intent(this, PlayerService::class.java).apply {
            action = ACTION_NEXT
        }
        val nextPendingIntent = PendingIntent.getService(
            this, 2, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val backIntent = Intent(this, PlayerService::class.java).apply {
            action = ACTION_BACK
        }
        val backPendingIntent = PendingIntent.getService(
            this, 3, backIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        // NotificationManager
        createNotificationChannel()


        // Notification
        return NotificationCompat.Builder(this, ID)
            .setColor(1000)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(1)
            )
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.baseline_library_music_24)
            .addAction(R.drawable.baseline_skip_previous_24, NEXT, backPendingIntent)
            .addAction(R.drawable.baseline_pause, SWITCH, pausePendingIntent)
            .addAction(R.drawable.baseline_skip_next, BACK, nextPendingIntent)
            .setContentTitle(nameMusic)
            .setContentText("My Awesome Band")
            .build()
    }

    // NotificationManager
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "music"
            val importance = NotificationManager.IMPORTANCE_MIN
            val channel = NotificationChannel(ID, name, importance)
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val ACTION_PLAY = "ACTION_PLAY"
        const val ACTION_PAUSE = "ACTION_PAUSE"
        const val ACTION_NEXT = "ACTION_NEXT"
        const val ACTION_BACK = "ACTION_BACK"

        const val ID = "CHANNEL_ID"

        const val NEXT = "next"
        const val SWITCH = "switch"
        const val BACK = "back"
    }


}