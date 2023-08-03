package com.example.muplayer.di

import com.example.domain.repository.CoreContract
import com.example.domain.usecase.UseCaseCore
import com.example.domain.usecase.contract.UseCaseCoreContract
import com.example.domain.usecase.search.FindAllAudioFilesContract
import com.example.domain.usecase.search.SearchAudioContract
import com.example.domain.usecase.search.UseCaseFindAudio
import com.example.muplayer.MyViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchFileModule {

    @Provides
    fun provideSearchAudioContract(
        findAllAudioFilesContract: FindAllAudioFilesContract,
        useCaseCoreContract: UseCaseCoreContract,
    ): SearchAudioContract {
        return UseCaseFindAudio(
            findAllAudioFilesContract,
            useCaseCoreContract
        )
    }

    @Provides
    fun provideUseCaseCoreContractPres(
        coreContract: CoreContract
    ): UseCaseCoreContract {
        return UseCaseCore(coreContract)
    }

    @Provides
    fun provideFindAllAudioFilesContractPres(): FindAllAudioFilesContract {
       return
    }


    /*@Provides
    fun provideViewModel(searchFilePresentation: SearchFilePresentation): MyViewModel {
        return MyViewModel(searchFilePresentation)
    }

    @Provides
    fun provideSearchFile(
        searchFileContact: SearchFileData,
        coreContract: CoreContract
    ): SearchFilePresentation {
        return UseCaseSearchFile(searchFileContact, coreContract)
    }*/

    /*    @Provides
        fun provideSearchFileData(@ApplicationContext context: Context): SearchFileData {
            return FindAllAudioFiles(context.contentResolver)
        }

        @Provides
        fun provideCoreContract(
            coreDao: CoreDao,
            coreEntityMapper: CoreEntityMapper<CoreEntity>
        ): CoreContract {
            return CoreRepositoryImpl(coreDao, coreEntityMapper)
        }*/
}