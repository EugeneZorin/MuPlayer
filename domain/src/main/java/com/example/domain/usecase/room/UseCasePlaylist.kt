package com.example.domain.usecase.room

import com.example.domain.entity.PlaylistEntityModel
import com.example.domain.repository.room.PlaylistsContractDt
import com.example.domain.usecase.room.contract.PlaylistContractPres

class UseCasePlaylist(
    private val playlistsContract: PlaylistsContractDt,
) : PlaylistContractPres {


    override suspend fun createPlaylistContract(playlistEntityModel: PlaylistEntityModel) {
        playlistsContract.insertPlaylist(playlistEntityModel)
    }

    override suspend fun delete(id: String) {
        playlistsContract.delete(id)
    }

    override suspend fun searchSong(id: String): Map<String, String>? {
        return playlistsContract.searchSong(id)
    }

    override suspend fun getAllPlaylist(): List<PlaylistEntityModel> {
        return playlistsContract.getAllPlaylist()
    }

}