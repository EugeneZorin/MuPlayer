package com.example.domain.usecase.smusic

import com.example.domain.entity.CoreEntityModel
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.smusic.MusicSwitchPres
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.room.contract.CoreContractPres

class MusicSwitch (
    private var playerStatePres: PlayerStatePres,
    private var useCaseCoreContract: CoreContractPres,
) : MusicSwitchPres {

    override suspend fun nextMusic() {

        // Current player position
        val currentPosition = playerStatePres.getData().position

        // All database
        val allData = useCaseCoreContract.getAllCore()

        try {
            if ((currentPosition.toInt()) == allData.size){
                updateEntityModel(ZERO, allData)
            } else {
                updateEntityModel(currentPosition.toInt(), allData)
            }
        } catch (e: Exception){
            errorHandler(e)
        }

    }

    override suspend fun backMusic() {

        // Current player position
        val currentPosition = playerStatePres.getData().position

        // All database
        val allData = useCaseCoreContract.getAllCore()

        try {
            if ((currentPosition.toInt()) == allData.size){
                updateEntityModel(ZERO, allData)
            } else {
                updateEntityModel(currentPosition.toInt(), allData)
            }
        } catch (e: Exception){
            errorHandler(e)
        }

    }

    private suspend fun updateEntityModel(it: Int, allData: List<CoreEntityModel>){
        playerStatePres.updateData(
            data = PlayerEntityModel(
                nameMusic = allData[it].nameMusic,
                idMusic = allData[it].idMusic,
                position = allData[it].id!!
            )
        )
    }

    private fun errorHandler(e: Exception) {
        println("Error update PlayerEntityModel in MusicSwitch ${e.message}")
    }

    companion object {
        const val ZERO = 0
    }
}