package com.example.presentation.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.session.MediaSession
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.media3.common.AudioAttributes
import androidx.media3.common.ForwardingPlayer
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.domain.entity.CoreEntityModel
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.room.contract.CoreContractPres
import com.example.presentation.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PlayerService : Service()  {

    @Inject
    lateinit var playerStatePres: PlayerStatePres

    private lateinit var player: ExoPlayer
    private lateinit var mediaItem: MediaItem


    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this).build()



        CoroutineScope(Dispatchers.Main).launch {

            mediaItem = MediaItem.fromUri(playerStatePres.getData().idMusic)

            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()


        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        val notification = notification()
        startForeground(1, notification)


        when(intent?.action){
            ACTION_PAUSE -> {
                player.pause()
            }
            ACTION_PLAY -> {
                player.play()
            }
            ACTION_NEXT -> {
                TODO()
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


        val notification = NotificationCompat.Builder(this, "channelId")
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .addAction(R.drawable.queue_music, "Play", playPendingIntent)
            .addAction(R.drawable.queue_music, "Pause", pausePendingIntent)
            .addAction(R.drawable.queue_music, "Next", nextPendingIntent)
            .setContentTitle("Wonderful music")
            .setContentText("My Awesome Band")

        return notification.build()
    }


    companion object {
        const val ACTION_PLAY = "ACTION_PLAY"
        const val ACTION_PAUSE = "ACTION_PAUSE"
        const val ACTION_NEXT = "ACTION_NEXT"
    }
}