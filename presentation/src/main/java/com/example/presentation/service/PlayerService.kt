package com.example.presentation.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.domain.entity.CoreEntityModel
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.usecase.datastory.contract.PlayerStatePres
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
class PlayerService: Service() {

    @Inject
    lateinit var playerStatePres: PlayerStatePres

    private lateinit var player: ExoPlayer
    private lateinit var mediaItem: MediaItem


    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this).build()

        CoroutineScope(Dispatchers.Main).launch{
            mediaItem = MediaItem.fromUri(playerStatePres.getData().idMusic)
        }


    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notification = notification()
        startForeground(1, notification)


        CoroutineScope(Dispatchers.Main).launch {
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
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

        val notification = NotificationCompat.Builder(this, "channelId")
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Wonderful music")
            .setContentText("My Awesome Band")

        return notification.build()
    }

    private fun createNotification(): Notification {
        createNotificationChannel()

        val notificationBuilder = NotificationCompat.Builder(this, "channelId")
            .setContentTitle("Example Service")
            .setContentText("Running...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        return notificationBuilder.build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                "channelId",
                "Example Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(serviceChannel)
        }
    }
}