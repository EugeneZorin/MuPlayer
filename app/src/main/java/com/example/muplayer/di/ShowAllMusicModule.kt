package com.example.muplayer.di

import android.content.Context
import com.example.data.datastore.DataStatePlayer
import com.example.data.datastore.PlayerData
import com.example.data.datastore.mappers.PlayerMapper
import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImplDt
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.datastory.PlayerStateDt
import com.example.domain.repository.mappers.CoreEntityMapper
import com.example.domain.repository.mappers.PlayerDataMapper
import com.example.domain.repository.room.CoreContractDt
import com.example.domain.usecase.datastory.PlayerStateImpl
import com.example.domain.usecase.datastory.contract.PlayerStatePres
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
        playerStatePres: PlayerStatePres
    ): MainViewModel {
        return MainViewModel(useCaseCoreContract, playerStatePres)
    }

    @Provides
    fun providePlayerStatePres(
        playerStateContract: PlayerStateDt
    ): PlayerStatePres {
        return PlayerStateImpl(playerStateContract)
    }

    @Provides
    fun provideDataStatePlayer(
        @ApplicationContext context: Context,
        playerMapper: PlayerMapper
    ): PlayerStateDt {
        return DataStatePlayer(context,playerMapper)
    }

    @Provides
    fun providePlayerMapper(): PlayerMapper {
        return PlayerMapper()
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