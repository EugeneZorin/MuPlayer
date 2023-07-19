package com.example.data.room.repository

import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreEntity
import com.example.domain.entity.CoreEntityModel
import com.example.domain.repository.CoreContract
import com.example.domain.repository.mappers.CoreEntityMapper

class CoreRepositoryImpl(
    private val coreDao: CoreDao,
    private val coreEntityMapper: CoreEntityMapper<CoreEntity>
): CoreContract {

    override suspend fun delete(id: Long){
        val coreEntities = coreDao.deleteSong(id)

    }

    override suspend fun insert(coreEntity: CoreEntityModel) {
        val coreEntities = coreDao.insertSong(CoreEntity(
            id = coreEntity.id,
            nameMusic = coreEntity.nameMusic,
            idMusic = coreEntity.idMusic
        ))
    }

    override suspend fun search(name: String): List<CoreEntityModel>{
        val coreEntities = coreDao.searchSong(name)
        return coreEntities.map {
            coreEntityMapper.mapToDomain(it)
        }
    }




}
