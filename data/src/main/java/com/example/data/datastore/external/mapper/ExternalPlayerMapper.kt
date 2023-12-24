package com.example.data.datastore.external.mapper

import com.example.data.datastore.external.ExternalPlayerData
import com.example.domain.entity.PlayerExternalModel
import com.example.domain.repository.mappers.ExternalDataMapper

class ExternalPlayerMapper: ExternalDataMapper<ExternalPlayerData> {
    override fun mapToDomain(entity: ExternalPlayerData): PlayerExternalModel {
        return PlayerExternalModel(
            pauseStop = entity.pauseStop,
            position = entity.position
        )

    }
}