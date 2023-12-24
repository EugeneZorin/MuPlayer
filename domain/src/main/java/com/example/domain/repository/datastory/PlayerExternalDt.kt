package com.example.domain.repository.datastory

import com.example.domain.entity.PlayerExternalModel

interface PlayerExternalDt {
    suspend fun updatePauseStop(externalPlayerData: PlayerExternalModel)
    suspend fun getData(): PlayerExternalModel
    suspend fun updatePosition(externalPlayerData: PlayerExternalModel)

    fun addListener(listener: () -> Unit)

}