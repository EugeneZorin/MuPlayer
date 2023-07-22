package com.example.domain.repository

import com.example.domain.entity.AlbumEntityModel

interface AlbumContract {
    suspend fun insertAlbum(albumsEntity: AlbumEntityModel)
    suspend fun delete(id: String)
    suspend fun searchSong(id: String): Map<String, String>?
}