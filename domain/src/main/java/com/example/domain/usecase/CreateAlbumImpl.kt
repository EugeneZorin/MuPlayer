package com.example.domain.usecase

import com.example.domain.entity.AlbumEntityModel
import com.example.domain.repository.AlbumContract
import com.example.domain.repository.CoreContract
import com.example.domain.usecase.contract.CreateAlbumContract

class CreateAlbumImpl(
    private val albumContract: AlbumContract,
) : CreateAlbumContract {
    override suspend fun createAlbumContract(id: String, albumList: Map<String, String>?) {
        albumContract.insertAlbum(AlbumEntityModel(id = id, albumList = albumList))
    }
}