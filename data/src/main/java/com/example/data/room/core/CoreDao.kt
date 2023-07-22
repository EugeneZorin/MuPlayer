package com.example.data.room.core

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(coreEntity: CoreEntity)

    @Delete
    suspend fun deleteSong(coreEntity: CoreEntity)

    @Query("SELECT * FROM CoreEntity WHERE nameMusic = :name")
    suspend fun searchSong(name: String): List<CoreEntity>

}