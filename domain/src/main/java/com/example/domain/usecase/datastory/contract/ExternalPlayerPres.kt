package com.example.domain.usecase.datastory.contract

import com.example.domain.entity.PlayerExternalModel

interface ExternalPlayerPres {
    suspend fun updatePauseStop(externalPlayerData: PlayerExternalModel)
    suspend fun getData(): PlayerExternalModel
    suspend fun updatePosition(externalPlayerData: PlayerExternalModel)
    fun addListener(listener: () -> Unit)
}