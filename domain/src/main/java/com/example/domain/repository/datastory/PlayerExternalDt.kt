package com.example.domain.repository.datastory

import com.example.domain.entity.PlayerExternalModel

interface PlayerExternalDt {
    suspend fun saveData(externalPlayerData: PlayerExternalModel)
    suspend fun getData(): PlayerExternalModel

}