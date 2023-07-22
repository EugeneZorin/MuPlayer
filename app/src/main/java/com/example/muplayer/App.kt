package com.example.muplayer

import android.app.Application
import com.example.data.room.core.CoreDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    val coreDatabase by lazy {
        CoreDatabase.database(this)
    }



}