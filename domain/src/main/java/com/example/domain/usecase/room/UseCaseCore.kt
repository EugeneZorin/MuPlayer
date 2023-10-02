package com.example.domain.usecase.room

import com.example.domain.entity.CoreEntityModel
import com.example.domain.repository.room.CoreContractDt
import com.example.domain.usecase.contract.room.CoreContractPres

class UseCaseCore(
    private val coreContract: CoreContractDt
): CoreContractPres {
    override suspend fun insertSong(coreEntity: CoreEntityModel) {
        coreContract.insert(coreEntity)
    }

    override suspend fun delete(id: Long) {
        coreContract.delete(id)
    }

    override suspend fun searchSong(name: String): List<CoreEntityModel> {
        return coreContract.search(name)
    }

    override suspend fun getAllCore(): List<CoreEntityModel> {
        return coreContract.getAllCore()
    }
}