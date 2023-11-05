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
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.domain.entity.PlayerEntityModel
import com.example.presentation.R
import com.example.presentation.viewmodels.ViewModelPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlayerService: Service() {

    private lateinit var viewModelPlayer: ViewModelPlayer
    private lateinit var job: Job
    private lateinit var player: ExoPlayer
    private lateinit var mediaItem: MediaItem



    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this).build()
        viewModelPlayer = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(ViewModelPlayer::class.java)
        job = Job()
        mediaItem = MediaItem.fromUri("/storage/emulated/0/Download/Overlord III - Opening _ VORACITY (320 kbps).mp3")

    }


    private fun updatePlayerState(newState: PlayerEntityModel) {
        viewModelPlayer.updatePlayerState(newState)
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = createNotification()
        startForeground(1, notification)
      /*  startMusicPlayer()*/

        return START_NOT_STICKY
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    private fun startMusicPlayer() {
        if (viewModelPlayer.pauseState.value == true) {

            CoroutineScope(Dispatchers.Main + job).launch {
                while (viewModelPlayer.pauseState.value == true) {
                    viewModelPlayer.process.value =
                        player.currentPosition.toFloat() / player.duration.toFloat()
                    delay(16)
                }
            }

        }

        CoroutineScope(Dispatchers.IO).launch {
            player.setMediaItem(mediaItem)
            player.prepare()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
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