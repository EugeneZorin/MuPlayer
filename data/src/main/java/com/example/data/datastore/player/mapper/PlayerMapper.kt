package com.example.data.datastore.player.mapper

import com.example.data.datastore.player.PlayerData
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.mappers.PlayerDataMapper

class PlayerMapper: PlayerDataMapper<PlayerData> {
    override fun mapToDomain(entity: PlayerData): PlayerEntityModel {
        return PlayerEntityModel(
            nameMusic = entity.nameMusic,
            idMusic = entity.idMusic,
            position = entity.position,
            isPlaying = entity.isPlaying
        )

    }
}