package com.example.domain.repository.mappers

import com.example.domain.entity.PlayerEntityModel

interface PlayerDataEntityMapper<T> {
    fun mapToDomain(entity: T): PlayerEntityModel
}