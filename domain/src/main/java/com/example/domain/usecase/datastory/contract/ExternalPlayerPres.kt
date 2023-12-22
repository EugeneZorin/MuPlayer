package com.example.domain.usecase.datastory.contract

import com.example.domain.entity.PlayerExternalModel

interface ExternalPlayerPres {
    suspend fun saveData(externalPlayerData: PlayerExternalModel)
    suspend fun getData(): PlayerExternalModel
}