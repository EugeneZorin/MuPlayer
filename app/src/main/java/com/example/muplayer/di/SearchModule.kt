package com.example.muplayer.di

import com.example.domain.usecase.datastory.contract.PlayerStatePres
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {
/*
    @Provides
    fun provideViewModel(
        playerStatePres: PlayerStatePres,
    ): ViewModelPlayer {
        return ViewModelPlayer(playerStatePres)
    }
*/


  /*  @Provides
    fun provideSearchAudioContract(
        findAllAudioFilesContract: FindAllAudioFilesContract,
        useCaseCoreContract: CoreContractPres,
    ): SearchAudioContract {
        return UseCaseSearchAudio(findAllAudioFilesContract, useCaseCoreContract)
    }


    @Provides
    fun provideFindAllAudioFilesContract(
        @ApplicationContext context: Context,
    ): FindAllAudioFilesContract {
        return FindAllAudioFiles(context.contentResolver)
    }*/

   /* @Provides
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
