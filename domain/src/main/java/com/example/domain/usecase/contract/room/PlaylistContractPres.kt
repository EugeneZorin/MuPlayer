package com.example.domain.usecase.contract.room

import com.example.domain.entity.PlaylistEntityModel

interface PlaylistContractPres {
    suspend fun createAlbumContract(id: String, albumList: Map<String, String>?)
    suspend fun delete(id: String)
    suspend fun searchSong(id: String): Map<String, String>?
    suspend fun getAllAlbums(): List<PlaylistEntityModel>

}