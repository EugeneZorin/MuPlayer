package com.example.data.room.core

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(coreEntity: CoreEntity)



}