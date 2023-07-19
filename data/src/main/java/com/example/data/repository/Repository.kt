package com.example.data.repository

import android.content.Context
import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreDatabase
import com.example.data.room.core.CoreEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository(private val context: Context) {

    private val coreDatabase: CoreDatabase = CoreDatabase.database(context)
    private val coreDao: CoreDao = coreDatabase.coreDao

    suspend fun insert(coreEntity: CoreEntity){
        coreDao.insertItem(coreEntity)
    }


}
