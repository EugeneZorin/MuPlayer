package com.example.domain.usecase.contract

import com.example.domain.entity.CoreEntityModel

interface UseCaseCoreContract {
    suspend fun insertSong(coreEntity: CoreEntityModel)
    suspend fun delete(id: Long)
    suspend fun searchSong(name: String): List<CoreEntityModel>
    suspend fun getAllCore(): List<CoreEntityModel>
}