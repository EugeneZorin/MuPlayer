package com.example.muplayer.di

import android.app.Application
import com.example.data.room.aldums.AlbumsDatabase
import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreDatabase
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImpl
import com.example.data.room.repository.mappers.CoreMapperImpl
import com.example.domain.repository.CoreContract
import com.example.domain.repository.mappers.CoreEntityMapper
import com.example.domain.usecase.UseCaseCore
import com.example.domain.usecase.contract.UseCaseCoreContract
import com.example.presentation.viewmodel.ShowAllMusicViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ShowAllMusicModule {

    @Provides
    fun provideViewModel(
        useCaseCoreContract: UseCaseCoreContract,
    ): ShowAllMusicViewModel {
        return ShowAllMusicViewModel(useCaseCoreContract)
    }

    @Provides
    fun provideUseCaseCore(
        coreContract: CoreContract
    ): UseCaseCoreContract {
        return UseCaseCore(coreContract)
    }

    @Provides
    fun provideCoreRepositoryImpl(
        coreDao: CoreDao,
        coreEntityMapper: CoreEntityMapper<CoreEntity>
    ): CoreContract {
        return CoreRepositoryImpl(coreDao, coreEntityMapper)
    }

    @Provides
    fun provideCoreDao( coreDatabase: CoreDatabase ): CoreDao {
        return coreDatabase.coreDao
    }

    @Provides
    fun provideAlbumDatabase(application: Application): CoreDatabase {
        return CoreDatabase.database(application)
    }

    @Provides
    fun provideCoreEntityMapper(): CoreEntityMapper<CoreEntity> {
        return CoreMapperImpl()
    }






}