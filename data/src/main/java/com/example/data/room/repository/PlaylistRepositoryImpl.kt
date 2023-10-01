package com.example.data.room.repository

import com.example.data.room.playlist.PlaylistDao
import com.example.data.room.playlist.PlaylistEntity
import com.example.domain.entity.PlaylistEntityModel
import com.example.domain.repository.room.PlaylistsContract
import com.example.domain.repository.mappers.PlaylistEntityMapper
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistDao: PlaylistDao,
    private val albumEntityModel: PlaylistEntityMapper<PlaylistEntity>
): PlaylistsContract {

    override suspend fun insertAlbum(albumsEntity: PlaylistEntityModel) {
        playlistDao.insertAlbum(
            PlaylistEntity(
                id = albumsEntity.id,
                albumList = albumsEntity.albumList
            )
        )
    }

    override suspend fun searchSong(id: String): Map<String, String>? {
        return playlistDao.searchSong(id)
    }

    override suspend fun getAllAlbums(): List<PlaylistEntityModel> {
        val getAllAlbum = playlistDao.getAllAlbums()
        return getAllAlbum.map {
            albumEntityModel.mapToDomain(it)
        }
    }



    override suspend fun delete(id: String) {
        playlistDao.delete(id)
    }
}
