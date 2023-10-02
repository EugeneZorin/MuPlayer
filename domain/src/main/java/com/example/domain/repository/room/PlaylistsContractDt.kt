package com.example.domain.repository.room

import com.example.domain.entity.PlaylistEntityModel

interface PlaylistsContractDt {
    suspend fun insertAlbum(albumsEntity: PlaylistEntityModel)
    suspend fun delete(id: String)
    suspend fun searchSong(id: String): Map<String, String>?
    suspend fun getAllAlbums(): List<PlaylistEntityModel>

}