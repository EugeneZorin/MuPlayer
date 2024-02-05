package com.example.domain.entity

data class PlaylistEntityModel(
    val id: String,
    val playlist: Map<String, List<String>>?
)
