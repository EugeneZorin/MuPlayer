package com.example.muplayer.di

import com.example.domain.repository.datastory.PlayerStateDt
import com.example.domain.usecase.datastory.PlayerStateImpl
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped


@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {


    @ServiceScoped
    @Provides
    fun providePlayerState(
        playerStateContract: PlayerStateDt
    ): PlayerStatePres {
        return PlayerStateImpl(playerStateContract)
    }
}