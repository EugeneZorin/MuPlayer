package com.example.data.room.repository

import com.example.data.room.aldums.AlbumsDao
import com.example.domain.entity.AlbumEntityModel
import com.example.domain.repository.AlbumContract

class AlbumRepositoryImpl(
    private val albumsDao: AlbumsDao,
): AlbumContract {

    override suspend fun insertAlbum(albumsEntity: AlbumEntityModel) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun searchSong(id: String): Map<String, String>? {
        TODO("Not yet implemented")
    }
}