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

    override suspend fun nextMusic(){

        if ((getPosition().toInt()) == getAllData().size){
            playerStatePres.updateData(
                data = PlayerEntityModel(
                    nameMusic = getAllData()[0].nameMusic,
                    idMusic = getAllData()[0].idMusic,
                    position = getAllData()[0].id!!
                )
            )
        } else {
            playerStatePres.updateData(
                data = PlayerEntityModel(
                    nameMusic = getAllData()[getPosition().toInt()].nameMusic,
                    idMusic = getAllData()[getPosition().toInt()].idMusic,
                    position = getAllData()[getPosition().toInt()].id!!
                )
            )
        }
    }

    // Current player position
    private suspend fun getPosition() = withContext(Dispatchers.IO){
        return@withContext playerStatePres.getData().position
    }

    // All database
    private suspend fun getAllData() = withContext(Dispatchers.IO){
        return@withContext useCaseCoreContract.getAllCore()
    }
}