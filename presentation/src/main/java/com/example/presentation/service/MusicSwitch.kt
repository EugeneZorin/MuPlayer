package com.example.presentation.service

import android.util.Log
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.room.contract.CoreContractPres
import javax.inject.Inject

class MusicSwitch @Inject constructor(
    private var playerStatePres: PlayerStatePres,
    private var useCaseCoreContract: CoreContractPres
) {


    suspend fun nextMusic(){

        val allData = useCaseCoreContract.getAllCore()
        val position = playerStatePres.getData().position + 1

       /* try {
            playerStatePres.updateData(data = PlayerEntityModel(
                nameMusic = allData[position.toInt()].nameMusic,
                idMusic = allData[position.toInt()].idMusic,
                position = allData[position.toInt()].id!!
            ))
        } catch(e: Exception) {
            Log.d("ERROR_NEXT_MUSIC", "$e")
        }*/

    }

}