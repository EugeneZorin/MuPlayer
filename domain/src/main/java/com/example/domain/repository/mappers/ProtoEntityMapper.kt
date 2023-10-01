package com.example.domain.repository.mappers

import com.example.domain.entity.ProtoEntityModel

interface ProtoEntityMapper<T>{
    fun mapToDomain(entity: T): ProtoEntityModel
}