package com.example.domain.usecase

import com.example.domain.entity.CoreEntityModel
import com.example.domain.repository.CoreContract
import com.example.domain.usecase.contract.UseCaseCoreContract

class UseCaseCore(
    private val coreContract: CoreContract
): UseCaseCoreContract{
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
        return coreContract.getAllAlbums()
    }
}