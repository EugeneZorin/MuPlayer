package com.example.muplayer.di

import com.example.data.room.aldums.AlbumsDao
import com.example.data.room.aldums.AlbumsEntity
import com.example.data.room.core.CoreEntity
import com.example.data.room.repository.AlbumRepositoryImpl
import com.example.data.room.repository.mappers.AlbumMapperImpl
import com.example.data.room.repository.mappers.CoreMapperImpl
import com.example.domain.repository.AlbumContract
import com.example.domain.repository.mappers.AlbumEntityMapper
import com.example.domain.repository.mappers.CoreEntityMapper
import com.example.domain.usecase.UseCaseAlbum
import com.example.domain.usecase.contract.UseCaseAlbumContract
import com.example.muplayer.MyViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CreateAlbumModule {

   @Provides
   fun provideViewModel(useCaseAlbumContract: UseCaseAlbumContract): MyViewModel{
       return MyViewModel(useCaseAlbumContract)
   }

    @Provides
    fun provideCreateAlbum(albumContract: AlbumContract): UseCaseAlbumContract{
        return UseCaseAlbum(albumContract = albumContract)
    }

    @Provides
    fun provideDaoAlbum(
        albumsDao: AlbumsDao,
        albumEntityModel: AlbumEntityMapper<AlbumsEntity>
    ): AlbumContract {
        return AlbumRepositoryImpl(
            albumsDao,
            albumEntityModel
        )
    }


}