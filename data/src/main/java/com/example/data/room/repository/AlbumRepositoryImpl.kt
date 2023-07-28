package com.example.data.room.repository

import com.example.data.room.aldums.AlbumsDao
import com.example.data.room.aldums.AlbumsEntity
import com.example.domain.entity.AlbumEntityModel
import com.example.domain.repository.AlbumContract
import com.example.domain.repository.mappers.AlbumEntityMapper
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumsDao: AlbumsDao,
): AlbumContract {

    override suspend fun insertAlbum(albumsEntity: AlbumEntityModel) {
        albumsDao.insertAlbum(
            AlbumsEntity(
                id = albumsEntity.id,
                albumList = albumsEntity.albumList)
        )
    }

    override suspend fun searchSong(id: String): Map<String, String>? {
        return albumsDao.searchSong(id)
    }

    override suspend fun delete(id: String) {
        albumsDao.delete(id)
    }
}
