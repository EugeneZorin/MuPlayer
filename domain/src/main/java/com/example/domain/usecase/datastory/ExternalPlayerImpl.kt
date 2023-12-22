package com.example.domain.usecase.datastory

import com.example.domain.entity.PlayerExternalModel
import com.example.domain.repository.datastory.PlayerExternalDt
import com.example.domain.usecase.datastory.contract.ExternalPlayerPres

class ExternalPlayerImpl(
    private val playerExternalDt: PlayerExternalDt
): ExternalPlayerPres{
    override suspend fun saveData(externalPlayerData: PlayerExternalModel) {
        playerExternalDt.saveData(
            externalPlayerData.copy(
                pauseStop = externalPlayerData.pauseStop,
                position = externalPlayerData.position
            )
        )
    }

    override suspend fun getData(): PlayerExternalModel {
        return playerExternalDt.getData()
    }

}