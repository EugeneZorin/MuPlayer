package com.example.muplayer.di

import android.app.Application
import com.example.data.room.core.CoreDao
import com.example.data.room.core.CoreDatabase
import com.example.data.room.core.CoreEntity
import com.example.data.room.playlist.PlaylistDao
import com.example.data.room.playlist.PlaylistDatabase
import com.example.data.room.playlist.PlaylistEntity
import com.example.data.room.repository.CoreRepositoryImplDt
import com.example.data.room.repository.PlaylistRepositoryImpl
import com.example.data.room.repository.mappers.CoreMapperImpl
import com.example.data.room.repository.mappers.PlaylistMapperImpl
import com.example.domain.repository.mappers.CoreEntityMapper
import com.example.domain.repository.mappers.PlaylistEntityMapper
import com.example.domain.repository.room.PlaylistsContractDt
import com.example.domain.usecase.room.contract.PlaylistContractPres
import com.example.presentation.viewmodels.ViewModelPlayList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object PlaylistModule {

    @Provides
    fun providePlaylistRepository(
        playlistDao: PlaylistDao,
        coreEntityMapper: PlaylistEntityMapper<PlaylistEntity>
    ): PlaylistsContractDt {
        return PlaylistRepositoryImpl(playlistDao, coreEntityMapper)
    }

    @Provides
    fun provideCoreDatabase(application: Application): PlaylistDatabase {
        return PlaylistDatabase.database(application)
    }


    @Provides
    fun provideCoreDao(playlistDatabase: PlaylistDatabase): PlaylistDao {
        return playlistDatabase.playlistDao
    }

    @Provides
    fun provideCoreEntityMapper(): PlaylistEntityMapper<PlaylistEntity> {
        return PlaylistMapperImpl()
    }

    @Provides
    fun provideViewModelPlayList(
        playlistContractPres: PlaylistContractPres
    ): ViewModelPlayList {
        return ViewModelPlayList(playlistContractPres)
    }
}