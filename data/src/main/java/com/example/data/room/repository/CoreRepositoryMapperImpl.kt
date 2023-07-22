package com.example.data.room.repository

import com.example.data.room.core.CoreEntity
import com.example.domain.entity.CoreEntityModel
import com.example.domain.repository.mappers.CoreEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoreRepositoryMapperImpl @Inject constructor(): CoreEntityMapper<CoreEntity> {
    override fun mapToDomain(entity: CoreEntity): CoreEntityModel {
       return CoreEntityModel(
           id = entity.id,
           nameMusic = entity.nameMusic,
           idMusic = entity.idMusic
       )
    }
}