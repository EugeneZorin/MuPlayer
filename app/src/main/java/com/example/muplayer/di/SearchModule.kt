package com.example.muplayer.di

import dagger.Module
import dagger.hilt.InstallIn
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
