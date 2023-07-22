package com.example.data.room.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import javax.inject.Inject

@Database(
    entities = [
        CoreEntity::class
    ],
    version = 1
)
abstract class CoreDatabase: RoomDatabase() {

    abstract val coreDao: CoreDao
    companion object{
        fun database(context: Context): CoreDatabase {
            return Room.databaseBuilder(
                context,
                CoreDatabase::class.java,
                "CoreEntity"
            ).build()
        }
    }

}