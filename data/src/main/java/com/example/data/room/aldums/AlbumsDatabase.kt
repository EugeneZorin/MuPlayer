package com.example.data.room.aldums

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.room.converter.AlbumConverter

@Database(
    entities = [
        AlbumsEntity::class
    ],
    version = 1
)
@TypeConverters(AlbumConverter::class)
abstract class AlbumsDatabase: RoomDatabase() {
    abstract fun albumsDao(): AlbumsDao

    companion object{
        fun database(context: Context): AlbumsDatabase {
            return Room.databaseBuilder(
                context,
                AlbumsDatabase::class.java,
                "albums_entity"
            ).build()
        }
    }
}