package com.example.data.room.playlist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.room.converter.PlaylistConverter

@Database(
    entities = [
        PlaylistEntity::class
    ],
    version = 2
)
@TypeConverters(PlaylistConverter::class)
abstract class PlaylistDatabase: RoomDatabase() {
    abstract fun albumsDao(): PlaylistDao

    companion object{
        fun database(context: Context): PlaylistDatabase {
            return Room.databaseBuilder(
                context,
                PlaylistDatabase::class.java,
                "albums_entity"
            ).build()
        }
    }
}