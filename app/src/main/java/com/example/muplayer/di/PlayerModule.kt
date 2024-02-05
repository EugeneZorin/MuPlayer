package com.example.muplayer.di

import android.app.Application
import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreDatabase
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImplDt
import com.example.data.room.repository.mappers.CoreMapperImpl
import com.example.domain.repository.mappers.CoreEntityMapper
import com.example.domain.repository.preferences.FirstRunPres
import com.example.domain.usecase.datastory.contract.ExternalPlayerPres
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.room.contract.CoreContractPres
import com.example.domain.usecase.room.contract.PlaylistContractPres
import com.example.domain.usecase.search.SearchAudioContract
import com.example.presentation.viewmodels.MainViewModel
import com.example.presentation.viewmodels.ViewModelPlayList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {

    @Provides
    fun provideViewModel(
        useCaseCoreContract: CoreContractPres,
        playlistContractPres: PlaylistContractPres,
        playerStatePres: PlayerStatePres,
        searchAudioContract: SearchAudioContract,
        firstRunPres: FirstRunPres,
        externalPlayerPres: ExternalPlayerPres
    ): MainViewModel {
        return MainViewModel(
            useCaseCoreContract,
            playlistContractPres,
            playerStatePres,
            searchAudioContract,
            firstRunPres,
            externalPlayerPres
        )
    }

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