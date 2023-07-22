package com.example.domain.repository.mappers

import com.example.domain.entity.CoreEntityModel

interface CoreEntityMapper<T>  {
    fun mapToDomain(entity: T): CoreEntityModel
}