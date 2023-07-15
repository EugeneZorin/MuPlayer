package com.example.data.room.aldums

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AlbumsEntity::class], version = 1)
abstract class AlbumsDatabase: RoomDatabase() {
    abstract fun albumsDao(): AlbumsDao
}