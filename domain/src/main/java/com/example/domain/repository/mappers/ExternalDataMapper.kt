package com.example.domain.repository.mappers

import com.example.domain.entity.PlayerExternalModel

interface ExternalDataMapper<T> {
    fun mapToDomain(entity: T): PlayerExternalModel
}