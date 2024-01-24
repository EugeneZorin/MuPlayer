package com.example.domain.usecase.room.contract

import com.example.domain.entity.PlaylistEntityModel

interface PlaylistContractPres {
    suspend fun createPlaylistContract(playlistEntityModel: PlaylistEntityModel)
    suspend fun delete(id: String)
    suspend fun searchSong(id: String): Map<String, String>?
    suspend fun getAllPlaylist(): List<PlaylistEntityModel>

}