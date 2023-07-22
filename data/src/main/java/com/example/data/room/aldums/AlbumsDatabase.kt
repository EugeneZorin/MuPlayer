package com.example.data.room.aldums

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.room.core.CoreDatabase

@Database(
    entities = [
        AlbumsEntity::class
    ],
    version = 1
)
abstract class AlbumsDatabase: RoomDatabase() {
    abstract fun albumsDao(): AlbumsDao

    companion object{
        fun database(context: Context): CoreDatabase {
            return Room.databaseBuilder(
                context,
                CoreDatabase::class.java,
                "albums_entity"
            ).build()
        }
    }
}