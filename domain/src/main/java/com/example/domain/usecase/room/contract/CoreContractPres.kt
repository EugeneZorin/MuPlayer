package com.example.domain.usecase.room.contract

import com.example.domain.entity.CoreEntityModel

interface CoreContractPres {
    suspend fun insertSong(coreEntity: CoreEntityModel)
    suspend fun delete(id: Long)
    suspend fun searchSong(name: String): List<CoreEntityModel>
    suspend fun getMusic(id: Int): List<CoreEntityModel>
    suspend fun getAllCore(): List<CoreEntityModel>
}