package com.example.muplayer

import android.app.Application
import com.example.data.room.aldums.AlbumsDao
import com.example.data.room.aldums.AlbumsDatabase
import com.example.data.room.aldums.AlbumsEntity
import com.example.data.room.repository.AlbumRepositoryImpl
import com.example.data.room.repository.mappers.AlbumMapperImpl
import com.example.domain.repository.AlbumContract
import com.example.domain.repository.mappers.AlbumEntityMapper
import com.example.domain.usecase.UseCaseAlbum
import com.example.domain.usecase.contract.UseCaseAlbumContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AlbumModule {


    @Provides
    fun provideMyViewModel(
        useCaseAlbumContract: UseCaseAlbumContract,
    ): MyViewModel {
        return MyViewModel(useCaseAlbumContract)
    }

    @Provides
    fun provideUseCaseAlbumContract(
        albumContract: AlbumContract
    ): UseCaseAlbumContract {
        return UseCaseAlbum(albumContract)
    }

    @Provides
    fun provideAlbumRepository(
        albumsDao: AlbumsDao,
        albumEntityModel: AlbumEntityMapper<AlbumsEntity>
    ): AlbumRepositoryImpl {
        return AlbumRepositoryImpl(albumsDao, albumEntityModel)
    }

    @Provides
    fun provideAlbumDatabase(application: Application ): AlbumsDatabase{
        return AlbumsDatabase.database(application)
    }


    @Provides
    fun provideAlbumDao( albumsDatabase: AlbumsDatabase ): AlbumsDao {
        return albumsDatabase.albumsDao()
    }

    @Provides
    fun provideAlbumEntityMapper(): AlbumEntityMapper<AlbumsEntity> {
        return AlbumMapperImpl()
    }


}