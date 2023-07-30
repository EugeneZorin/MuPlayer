package com.example.domain.usecase.contract

interface CreateAlbumContract {
    suspend fun createAlbumContract(id: String, albumList: Map<String, String>?)
}