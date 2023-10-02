package com.example.data.room.repository.mappers

import com.example.data.datastore.PlayerData
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.mappers.PlayerDataEntityMapper
import javax.inject.Singleton
@Singleton
class PlayerDataMapperImpl: PlayerDataEntityMapper<PlayerData> {
    override fun mapToDomain(entity: PlayerData): PlayerEntityModel {
        return PlayerEntityModel(
            time = entity.time,
            nameMusic = entity.nameMusic,
            idMusic = entity.idMusic,
            position = entity.position
        )
    }
}