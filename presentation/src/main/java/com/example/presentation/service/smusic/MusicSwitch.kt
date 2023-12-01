package com.example.presentation.service.smusic

import com.example.domain.entity.CoreEntityModel
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.room.contract.CoreContractPres
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MusicSwitch @Inject constructor(
    private var playerStatePres: PlayerStatePres,
    private var useCaseCoreContract: CoreContractPres,
) : MusicSwitchContract {

    override suspend fun nextMusic() = withContext(Dispatchers.Main){

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