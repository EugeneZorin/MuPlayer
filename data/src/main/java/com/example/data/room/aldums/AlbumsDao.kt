package com.example.data.room.aldums

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AlbumsDao {

    @Query("SELECT * FROM albums_entity")
    fun getAll(): List<AlbumsEntity>

}