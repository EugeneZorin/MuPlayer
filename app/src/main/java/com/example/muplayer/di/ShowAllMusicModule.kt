package com.example.muplayer.di

import android.content.Context
import com.example.data.datastore.DataStatePlayer
import com.example.data.datastore.mappers.PlayerMapper
import com.example.data.preferences.FirstRun
import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImplDt
import com.example.data.search.FindAllAudioFiles
import com.example.domain.repository.datastory.PlayerStateDt
import com.example.domain.repository.mappers.CoreEntityMapper
import com.example.domain.repository.preferences.FirstRinDt
import com.example.domain.repository.preferences.FirstRunPres
import com.example.domain.repository.room.CoreContractDt
import com.example.domain.repository.smusic.MusicSwitchPres
import com.example.domain.usecase.preferences.FirstRunImpl
import com.example.domain.usecase.SearchAudio
import com.example.domain.usecase.datastory.PlayerStateImpl
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.room.UseCaseCore
import com.example.domain.usecase.room.contract.CoreContractPres
import com.example.domain.usecase.search.FindAllAudioFilesContract
import com.example.domain.usecase.search.SearchAudioContract
import com.example.domain.usecase.smusic.MusicSwitch
import com.example.presentation.viewmodels.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShowAllMusicModule {
    @Provides
    fun provideViewModel(
        useCaseCoreContract: CoreContractPres,
        playerStatePres: PlayerStatePres,
        searchAudioContract: SearchAudioContract,
        firstRunPres: FirstRunPres,
        musicSwitchContract: MusicSwitchPres
    ): MainViewModel {
        return MainViewModel(useCaseCoreContract, playerStatePres, searchAudioContract, firstRunPres, musicSwitchContract)
    }

    @Provides
    fun provideFirstRunImpl(
        firstRinDt: FirstRinDt
    ): FirstRunPres{
        return FirstRunImpl(firstRinDt)
    }

    @Provides
    fun provideFirstRun(
        @ApplicationContext context: Context
    ): FirstRinDt {
        return FirstRun(context)
    }

    @Provides
    fun provideCaseSearchAudio(
        findAllAudioFilesContract: FindAllAudioFilesContract,
        useCaseCoreContract: CoreContractPres
    ): SearchAudioContract {
        return SearchAudio(findAllAudioFilesContract, useCaseCoreContract)
    }


    @Provides
    fun provideFindAllAudio(
        @ApplicationContext context: Context,
    ): FindAllAudioFilesContract{
        return FindAllAudioFiles(context.contentResolver)
    }

    @Singleton
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
    fun provideMusicSwitch(
        playerStatePres: PlayerStatePres,
        useCaseCoreContract: CoreContractPres
    ): MusicSwitchPres {
        return MusicSwitch(playerStatePres, useCaseCoreContract)
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