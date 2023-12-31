package com.example.muplayer.di

import android.app.Application
import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreDatabase
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImplDt
import com.example.data.room.repository.mappers.CoreMapperImpl
import com.example.domain.repository.datastory.PlayerExternalDt
import com.example.domain.repository.mappers.CoreEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PlaylistModule {


    @Provides
    fun provideCoreRepository(
        coreDao: CoreDao,
        coreEntityMapper: CoreEntityMapper<CoreEntity>
    ): CoreRepositoryImplDt {
        return CoreRepositoryImplDt(coreDao, coreEntityMapper)
    }

    @Provides
    fun provideCoreDatabase(application: Application): CoreDatabase {
        return CoreDatabase.database(application)
    }


    @Provides
    fun provideCoreDao(coreDatabase: CoreDatabase): CoreDao {
        return coreDatabase.coreDao
    }

    @Provides
    fun provideCoreEntityMapper(): CoreEntityMapper<CoreEntity> {
        return CoreMapperImpl()
    }






}