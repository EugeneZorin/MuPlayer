package com.example.data.datastore.mappers

import com.example.data.datastore.PlayerData
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.mappers.PlayerDataMapper

class PlayerMapper: PlayerDataMapper<PlayerData> {
    override fun mapToDomain(entity: PlayerData): PlayerEntityModel {
        return PlayerEntityModel(
            time = entity.time,
            nameMusic = entity.nameMusic,
            idMusic = entity.idMusic,
            position = entity.position
        )

    }
}