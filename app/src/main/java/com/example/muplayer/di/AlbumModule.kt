package com.example.muplayer.di

import com.example.domain.repository.AlbumContract
import com.example.domain.usecase.UseCaseAlbum
import com.example.domain.usecase.contract.UseCaseAlbumContract
import com.example.muplayer.MyViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AlbumModule {


   /* @Provides
    fun provideViewModel(useCaseAlbumContract: UseCaseAlbumContract): MyViewModel {
        return MyViewModel(useCaseAlbumContract)
    }*/

    @Provides
    fun provideCreateAlbum(albumContract: AlbumContract): UseCaseAlbumContract{
        return UseCaseAlbum(albumContract = albumContract)
    }




}
