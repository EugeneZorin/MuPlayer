package com.example.muplayer.di

import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImpl
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