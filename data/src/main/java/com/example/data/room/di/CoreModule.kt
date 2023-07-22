package com.example.data.room.di

import android.content.Context
import androidx.room.Room
import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreDatabase
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImpl
import com.example.data.room.repository.CoreRepositoryMapperImpl
import com.example.domain.repository.mappers.CoreEntityMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideCoreDao(coreDatabase: CoreDatabase): CoreDao {
        return coreDatabase.coreDao
    }


    @Provides
    fun provideCoreEntityMapper(): CoreEntityMapper<CoreEntity> {
        return CoreRepositoryMapperImpl()
    }

    @Provides
    fun provideCoreRepository(
        coreDao: CoreDao,
        coreEntityMapper: CoreEntityMapper<CoreEntity>
    ): CoreRepositoryImpl {
        return CoreRepositoryImpl(coreDao, coreEntityMapper)
    }
}