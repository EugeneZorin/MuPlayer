package com.example.muplayer.di

import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImplDt
import com.example.domain.repository.room.CoreContractDt
import com.example.domain.repository.mappers.CoreEntityMapper
import com.example.domain.usecase.room.UseCaseCore
import com.example.domain.usecase.contract.room.CoreContractPres
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
        useCaseCoreContract: CoreContractPres,
    ): MainViewModel {
        return MainViewModel(useCaseCoreContract)
    }

    @Provides
    fun provideUseCaseCoreContract(
        coreContract: CoreContractDt
    ): CoreContractPres {
        return UseCaseCore(coreContract)
    }

    @Provides
    fun provideCoreContract(
        coreDao: CoreDao,
        coreEntityMapper: CoreEntityMapper<CoreEntity>,
    ): CoreContractDt {
        return CoreRepositoryImplDt(
            coreDao, coreEntityMapper
        )
    }

}