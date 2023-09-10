package com.example.domain.repository.mappers

import com.example.domain.entity.PlaylistEntityModel

interface PlaylistEntityMapper<T> {
    fun mapToDomain(entity: T): PlaylistEntityModel
}