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
        mediaItem = MediaItem.fromUri("/storage/emulated/0/Music/Iosif_Kobzon_-_I_vnov_prodolzhaetsya_boj_(TheMP3.Info).mp3")


    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val music = intent?.getStringExtra("id_music").toString()

        Log.d("sdfaf","$music")


        val notification = createNotification()
        startForeground(1, notification)


        /*CoroutineScope(Dispatchers.Main).launch {


            mediaItem = MediaItem.fromUri(playerStatePres
                .getData()
                .idMusic[intent?.getStringExtra("id_music")!!.toInt()].toString())

            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
        }*/


        return START_STICKY
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
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