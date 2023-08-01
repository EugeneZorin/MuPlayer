package com.example.domain.repository.mappers

import com.example.domain.entity.AlbumEntityModel
import com.example.domain.entity.CoreEntityModel

interface AlbumEntityMapper<T> {
    fun mapToDomain(entity: T): AlbumEntityModel
}