package com.example.data.datastore.player


import kotlinx.serialization.Serializable

@Serializable
data class PlayerData(
    val nameMusic: String = "",
    val idMusic: String = "",
    val position: Long = 0,
    val isPlaying: Boolean = true
)
