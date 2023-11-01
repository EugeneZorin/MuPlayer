package com.example.presentation.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.presentation.R

class PlayerService: Service() {


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start(){
        val notification = NotificationCompat.Builder(this, "running_channel")
            .setContentTitle("Run is active")
            .setContentText("Elapse time: 00:51")
            .build()
        startForeground(
            1,
            notification
        )
    }

    enum class Actions {
        START, STOP
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }



}