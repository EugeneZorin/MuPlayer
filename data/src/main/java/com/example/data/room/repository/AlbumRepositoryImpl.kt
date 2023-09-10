package com.example.data.room.repository

import com.example.data.room.aldums.AlbumsDao
import com.example.data.room.aldums.AlbumsEntity
import com.example.domain.entity.PlaylistEntityModel
import com.example.domain.repository.PlaylistsContract
import com.example.domain.repository.mappers.PlaylistEntityMapper
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumsDao: AlbumsDao,
    private val albumEntityModel: PlaylistEntityMapper<AlbumsEntity>
): PlaylistsContract {

    override suspend fun insertAlbum(albumsEntity: PlaylistEntityModel) {
        albumsDao.insertAlbum(
            AlbumsEntity(
                id = albumsEntity.id,
                albumList = albumsEntity.albumList
            )
        )
    }

    override suspend fun searchSong(id: String): Map<String, String>? {
        return albumsDao.searchSong(id)
    }

    override suspend fun getAllAlbums(): List<PlaylistEntityModel> {
        val getAllAlbum = albumsDao.getAllAlbums()
        return getAllAlbum.map {
            albumEntityModel.mapToDomain(it)
        }
    }



    override suspend fun delete(id: String) {
        albumsDao.delete(id)
    }
}
