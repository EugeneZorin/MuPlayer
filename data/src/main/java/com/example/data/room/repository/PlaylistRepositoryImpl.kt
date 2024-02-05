package com.example.data.room.repository

import com.example.data.room.playlist.PlaylistDao
import com.example.data.room.playlist.PlaylistEntity
import com.example.domain.entity.PlaylistEntityModel
import com.example.domain.repository.mappers.PlaylistEntityMapper
import com.example.domain.repository.room.PlaylistsContractDt
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistDao: PlaylistDao,
    private val albumEntityModel: PlaylistEntityMapper<PlaylistEntity>
): PlaylistsContractDt {

    override suspend fun insertPlaylist(playlistEntity: PlaylistEntityModel) {
        playlistDao.insertPlaylist(
            PlaylistEntity(
                id = playlistEntity.id,
                playList = playlistEntity.playlist
            )
        )
    }

    override suspend fun searchSong(id: String): Map<String, List<String>>? {
        return playlistDao.searchSong(id)
    }



    override suspend fun getAllPlaylist(): List<PlaylistEntityModel> {
        val getAllAlbum = playlistDao.getAllPlaylist()
        return getAllAlbum.map {
            albumEntityModel.mapToDomain(it)
        }
    }



    override suspend fun delete(id: String) {
        playlistDao.delete(id)
    }
}
