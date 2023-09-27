package com.example.muplayer.di

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreDatabase
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImpl
import com.example.data.room.repository.mappers.CoreMapperImpl
import com.example.data.search.FindAllAudioFiles
import com.example.domain.repository.CoreContract
import com.example.domain.repository.mappers.CoreEntityMapper
import com.example.domain.usecase.UseCaseCore
import com.example.domain.usecase.contract.UseCaseCoreContract
import com.example.domain.usecase.search.FindAllAudioFilesContract
import com.example.domain.usecase.search.SearchAudioContract
import com.example.presentation.viewmodels.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {
/*
    @Provides
    fun provideViewModel(
        searchAudioContract: SearchAudioContract,
    ): MainViewModel {
        return MainViewModel(searchAudioContract)
    }


    @Provides
    fun provideSearchAudioContract(
        findAllAudioFilesContract: FindAllAudioFilesContract,
        useCaseCoreContract: UseCaseCoreContract,
    ): SearchAudioContract {
        return UseCaseFindAudio(findAllAudioFilesContract, useCaseCoreContract)
    }


    @Provides
    fun provideFindAllAudioFilesContract(
        @ApplicationContext context: Context,
    ): FindAllAudioFilesContract {
        return FindAllAudioFiles(context.contentResolver)
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
    }*/




}
