package com.example.muplayer.di

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.data.room.aldums.AlbumsDatabase
import com.example.data.room.aldums.AlbumsEntity
import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.CoreRepositoryImpl
import com.example.data.search.FindAllAudioFiles
import com.example.domain.repository.CoreContract
import com.example.domain.repository.mappers.CoreEntityMapper
import com.example.domain.usecase.search.SearchFileData
import com.example.domain.usecase.search.SearchFilePresentation
import com.example.domain.usecase.search.UseCaseSearchFile
import com.example.muplayer.MyViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchFileModule {
    @Provides
    fun provideViewModel(searchFilePresentation: SearchFilePresentation): MyViewModel {
        return MyViewModel(searchFilePresentation)
    }

    @Provides
    fun provideSearchFile(
        searchFileContact: SearchFileData,
        coreContract: CoreContract
    ): SearchFilePresentation {
        return UseCaseSearchFile(searchFileContact, coreContract)
    }

    @Provides
    fun provideSearchFileData(@ApplicationContext context: Context): SearchFileData {
        return FindAllAudioFiles(context.contentResolver)
    }

    @Provides
    fun provideCoreContract(
        coreDao: CoreDao,
        coreEntityMapper: CoreEntityMapper<CoreEntity>
    ): CoreContract {
        return CoreRepositoryImpl(coreDao, coreEntityMapper)
    }
}