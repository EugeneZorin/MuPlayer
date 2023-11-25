package com.example.presentation.service.smusic

import android.content.Intent
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.room.contract.CoreContractPres
import com.example.presentation.service.PlayerService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MusicSwitch @Inject constructor(
    private var playerStatePres: PlayerStatePres,
    private var useCaseCoreContract: CoreContractPres,
) : MusicSwitchContract {

    override suspend fun nextMusic() = withContext(Dispatchers.IO){

        // Current player position
        val currentPosition = playerStatePres.getData().position

        // All database
        val allData = useCaseCoreContract.getAllCore()

        if ((currentPosition.toInt()) == allData.size){
            playerStatePres.updateData(
                data = PlayerEntityModel(
                    nameMusic = allData[0].nameMusic,
                    idMusic = allData[0].idMusic,
                    position = allData[0].id!!
                )
            )
        } else {
            playerStatePres.updateData(
                data = PlayerEntityModel(
                    nameMusic = allData[currentPosition.toInt()].nameMusic,
                    idMusic = allData[currentPosition.toInt()].idMusic,
                    position = allData[currentPosition.toInt()].id!!
                )
            )
        }
    }
}