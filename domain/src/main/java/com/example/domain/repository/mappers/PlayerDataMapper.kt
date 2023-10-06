package com.example.domain.repository.mappers

import com.example.domain.entity.PlayerEntityModel

interface PlayerDataMapper<T> {
    fun mapToDomain(entity: T): PlayerEntityModel
}