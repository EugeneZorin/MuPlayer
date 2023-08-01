package com.example.domain.usecase

import com.example.domain.entity.AlbumEntityModel
import com.example.domain.repository.AlbumContract
import com.example.domain.usecase.contract.UseCaseAlbumContract

class UseCaseAlbum(
    private val albumContract: AlbumContract,
) : UseCaseAlbumContract {
    override suspend fun createAlbumContract(id: String, albumList: Map<String, String>?) {
        albumContract.insertAlbum(AlbumEntityModel(
            id = id,
            albumList = albumList)
        )
    }

    override suspend fun delete(id: String) {
        albumContract.delete(id)
    }

    override suspend fun searchSong(id: String): Map<String, String>? {
        return albumContract.searchSong(id)
    }

    override suspend fun getAllAlbums(): List<AlbumEntityModel> {
        return albumContract.getAllAlbums()
    }

}