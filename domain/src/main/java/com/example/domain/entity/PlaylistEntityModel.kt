package com.example.domain.entity

data class PlaylistEntityModel(
    val id: String,
    val albumList: Map<String, String>?
)
