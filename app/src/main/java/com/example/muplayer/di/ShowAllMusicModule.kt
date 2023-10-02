package com.example.muplayer.di

import android.content.Context
import com.example.data.datastore.DataStatePlayer
import com.example.data.datastore.PlayerData
import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImplDt
import com.example.data.room.repository.mappers.PlayerDataMapperImpl
import com.example.domain.repository.datastory.PlayerContractDt
import com.example.domain.repository.mappers.CoreEntityMapper
import com.example.domain.repository.mappers.PlayerDataEntityMapper
import com.example.domain.repository.room.CoreContractDt
import com.example.domain.usecase.datastory.UseCasePlayer
import com.example.domain.usecase.datastory.contract.PlayerContractPres
import com.example.domain.usecase.room.UseCaseCore
import com.example.domain.usecase.room.contract.CoreContractPres
import com.example.presentation.viewmodels.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ShowAllMusicModule {
    @Provides
    fun provideViewModel(
        useCaseCoreContract: CoreContractPres,
        playerContractPres: PlayerContractPres
    ): MainViewModel {
        return MainViewModel(useCaseCoreContract, playerContractPres)
    }

    @Provides
    fun providePlayerContractPres(
        playerContractDt: PlayerContractDt
    ): PlayerContractPres {
        return UseCasePlayer(playerContractDt)
    }

    @Provides
    fun provideDataStatePlayer(
        @ApplicationContext context: Context,
        playerDataEntityMapper: PlayerDataEntityMapper<PlayerData>
    ): PlayerContractDt {
        return DataStatePlayer(context, playerDataEntityMapper)
    }

    @Provides
    fun providePlayerMapper(): PlayerDataEntityMapper<PlayerData> {
        return PlayerDataMapperImpl()
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