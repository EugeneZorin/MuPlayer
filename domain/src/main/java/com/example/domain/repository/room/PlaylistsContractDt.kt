package com.example.domain.repository.room

import com.example.domain.entity.CoreEntityModel
import com.example.domain.entity.PlaylistEntityModel

interface PlaylistsContractDt {
    suspend fun insertPlaylist(playlistEntityModel: PlaylistEntityModel)
    suspend fun delete(id: String)
    suspend fun searchSong(id: String): Map<String, List<String>>?


    suspend fun getAllPlaylist(): List<PlaylistEntityModel>

}