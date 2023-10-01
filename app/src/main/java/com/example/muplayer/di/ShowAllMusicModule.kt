package com.example.muplayer.di

import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImpl
import com.example.domain.repository.room.CoreContract
import com.example.domain.repository.mappers.CoreEntityMapper
import com.example.domain.usecase.UseCaseCore
import com.example.domain.usecase.contract.room.UseCaseCoreContract
import com.example.presentation.viewmodels.MainViewModel

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
    ): MainViewModel {
        return MainViewModel(useCaseCoreContract)
    }

    @Provides
    fun provideUseCaseCoreContract(
        coreContract: CoreContract
    ): UseCaseCoreContract {
        return UseCaseCore(coreContract)
    }

    @Provides
    fun provideCoreContract(
        coreDao: CoreDao,
        coreEntityMapper: CoreEntityMapper<CoreEntity>,
    ): CoreContract {
        return CoreRepositoryImpl(
            coreDao, coreEntityMapper
        )
    }

}