package com.example.domain.usecase.contract

import com.example.domain.entity.AlbumEntityModel
import java.util.concurrent.Flow

interface UseCaseAlbumContract {
    suspend fun createAlbumContract(id: String, albumList: Map<String, String>?)
    suspend fun delete(id: String)
    suspend fun searchSong(id: String): Map<String, String>?

    suspend fun getAllAlbums(): List<AlbumEntityModel>

}