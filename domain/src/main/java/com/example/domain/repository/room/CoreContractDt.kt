package com.example.domain.repository.room

import com.example.domain.entity.CoreEntityModel

interface CoreContractDt {
    suspend fun search(name: String): List<CoreEntityModel>
    suspend fun delete(id: Long)
    suspend fun insert(coreEntity: CoreEntityModel)
    suspend fun getAllCore(): List<CoreEntityModel>
}