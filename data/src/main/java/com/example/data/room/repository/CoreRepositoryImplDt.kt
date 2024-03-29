package com.example.data.room.repository

import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreEntity
import com.example.domain.entity.CoreEntityModel
import com.example.domain.repository.room.CoreContractDt
import com.example.domain.repository.mappers.CoreEntityMapper
import javax.inject.Inject

class CoreRepositoryImplDt @Inject constructor(
    private val coreDao: CoreDao,
    private val coreEntityMapper: CoreEntityMapper<CoreEntity>
): CoreContractDt {


    override suspend fun insert(coreEntity: CoreEntityModel) {
        coreDao.insertSong(
            CoreEntity(
                id = coreEntity.id,
                nameMusic = coreEntity.nameMusic,
                idMusic = coreEntity.idMusic
            )
        )
    }

    override suspend fun getMusic(id: Int): List<CoreEntityModel> {
        val data = coreDao.getMusic(id)
        return data.map {
            coreEntityMapper.mapToDomain(it)
        }
    }

    override suspend fun getAllCore(): List<CoreEntityModel> {
        val getAll = coreDao.getAllCore()
        return getAll.map {
            coreEntityMapper.mapToDomain(it)
        }
    }

    override suspend fun search(name: String): List<CoreEntityModel>{
        val coreEntities = coreDao.searchSong(name)
        return coreEntities.map {
            coreEntityMapper.mapToDomain(it)
        }
    }

    override suspend fun delete(id: Long) {
        coreDao.delete(id)
    }


}
