package com.example.domain.usecase.room

import com.example.domain.entity.PlaylistEntityModel
import com.example.domain.repository.room.PlaylistsContractDt
import com.example.domain.usecase.room.contract.PlaylistContractPres

class UseCasePlaylist(
    private val albumContract: PlaylistsContractDt,
) : PlaylistContractPres {

    override suspend fun createAlbumContract(id: String, albumList: Map<String, String>?) {
        albumContract.insertAlbum(PlaylistEntityModel(
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

    override suspend fun getAllAlbums(): List<PlaylistEntityModel> {
        return albumContract.getAllAlbums()
    }

}