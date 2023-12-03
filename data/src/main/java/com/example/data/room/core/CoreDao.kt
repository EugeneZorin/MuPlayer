package com.example.data.room.core

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(coreEntity: CoreEntity)

    @Query("DELETE FROM core_entity WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM core_entity WHERE name_music = :name")
    suspend fun searchSong(name: String): List<CoreEntity>

    @Query("SELECT * FROM core_entity")
    suspend fun getAllCore(): List<CoreEntity>


}