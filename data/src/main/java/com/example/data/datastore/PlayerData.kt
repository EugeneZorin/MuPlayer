package com.example.data.datastore


import com.example.domain.entity.PlayerEntityModel
import kotlinx.serialization.Serializable

@Serializable
data class PlayerData(
    val time: Int = 0,
    val nameMusic: String = "",
    val idMusic: String = "",
    val position: Long = 0
)
