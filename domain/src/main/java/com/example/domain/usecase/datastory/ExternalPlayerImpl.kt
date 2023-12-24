package com.example.domain.usecase.datastory

import com.example.domain.entity.PlayerExternalModel
import com.example.domain.repository.datastory.PlayerExternalDt
import com.example.domain.usecase.datastory.contract.ExternalPlayerPres

class ExternalPlayerImpl(
    private val playerExternalDt: PlayerExternalDt,
) : ExternalPlayerPres {

    // Determines the status of the player whether it is running or paused for the entire application
    override suspend fun updatePauseStop(externalPlayerData: PlayerExternalModel) {
        return try {
            playerExternalDt.updatePauseStop(
                externalPlayerData.copy(
                    pauseStop = externalPlayerData.pauseStop
                )
            )

        } catch (e: Exception) {
            showError(e = e, name = 1 )
        }
    }

    // Saves how much time has passed since the start of a musical composition.
    // Allows you to pause and continue from the same place, also changing
    // this value at any point in the application will allow you to change
    // the time scale of the song, rewind or roll back
    override suspend fun updatePosition(externalPlayerData: PlayerExternalModel) {
        try {
            playerExternalDt.updatePosition(
                externalPlayerData.copy(
                    position = externalPlayerData.position
                )
            )
        } catch (e: Exception) {
            showError(e = e, name = 2 )
        }

    }

    // Getting the player status and position
    override suspend fun getData(): PlayerExternalModel {
        return playerExternalDt.getData()
    }

    // Monitors the change in updatePauseStop and reports it
    override fun addListener(listener: () -> Unit) {
        playerExternalDt.addListener(listener)
    }

    private fun showError(e: Exception, name: Int) {
        when (name){
            1 -> println("An error occurred during updatePauseStop: ${e.message}")
            2 -> println("An error occurred during updatePosition: ${e.message}")
        }

    }

}