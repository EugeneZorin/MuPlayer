package com.example.data.di

import android.content.Context
import com.example.data.search.FindAllAudioFiles
import com.example.domain.repository.CoreContract
import com.example.domain.usecase.UseCaseCore
import com.example.domain.usecase.contract.UseCaseCoreContract
import com.example.domain.usecase.search.FindAllAudioFilesContract
import com.example.domain.usecase.search.UseCaseFindAudio
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FindAudioModule {

    @Provides
    fun provideUseCaseFindAudio(
        findAllAudioFilesContract: FindAllAudioFilesContract,
        useCaseCoreContract: UseCaseCoreContract,
    ): UseCaseFindAudio {
        return UseCaseFindAudio(findAllAudioFilesContract, useCaseCoreContract)
    }

    @Provides
    fun provideFindAllAudioFilesContract(
        @ApplicationContext context: Context
    ): FindAllAudioFilesContract {
        return FindAllAudioFiles(context.contentResolver)
    }

    @Provides
    fun provideUseCaseCoreContract(coreContract: CoreContract): UseCaseCoreContract{
        return UseCaseCore(coreContract)
    }

}