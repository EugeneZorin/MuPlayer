package com.example.data.room.di

import android.app.Application
import com.example.data.room.aldums.AlbumsDao
import com.example.data.room.aldums.AlbumsDatabase
import com.example.data.room.repository.AlbumRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AlbumModule {


    @Provides
    fun provideAlbumRepository(
        albumsDao: AlbumsDao
    ): AlbumRepositoryImpl {
        return AlbumRepositoryImpl(albumsDao)
    }

    @Provides
    fun provideAlbumDatabase(application: Application ): AlbumsDatabase{
        return AlbumsDatabase.database(application)
    }

    @Provides
    fun provideAlbumDao( albumsDatabase: AlbumsDatabase ): AlbumsDao {
        return albumsDatabase.albumsDao()
    }


}