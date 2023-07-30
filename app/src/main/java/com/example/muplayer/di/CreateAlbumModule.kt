package com.example.muplayer.di

import com.example.data.room.aldums.AlbumsDao
import com.example.data.room.repository.AlbumRepositoryImpl
import com.example.domain.repository.AlbumContract
import com.example.domain.usecase.CreateAlbumImpl
import com.example.domain.usecase.contract.CreateAlbumContract
import com.example.muplayer.MyViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CreateAlbumModule {

   @Provides
   fun provideViewModel(createAlbumContract: CreateAlbumContract): MyViewModel{
       return MyViewModel(createAlbumContract)
   }

    @Provides
    fun provideCreateAlbum(albumContract: AlbumContract): CreateAlbumContract{
        return CreateAlbumImpl(albumContract = albumContract)
    }

    @Provides
    fun provideDaoAlbum(albumsDao: AlbumsDao): AlbumContract {
        return AlbumRepositoryImpl(albumsDao)
    }


}