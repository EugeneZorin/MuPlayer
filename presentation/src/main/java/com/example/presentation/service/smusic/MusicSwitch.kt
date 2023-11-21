package com.example.presentation.service.smusic

import android.util.Log
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.room.contract.CoreContractPres
import javax.inject.Inject

class MusicSwitch @Inject constructor(
    private var playerStatePres: PlayerStatePres,
    private var useCaseCoreContract: CoreContractPres,
) : MusicSwitchContract {

    override suspend fun nextMusic() {

        // Current player position
        val currentPosition = playerStatePres.getData().position

        // All database
        val allData = useCaseCoreContract.getAllCore()

        if ((currentPosition.toInt() + 1) == allData.size){
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
                    nameMusic = allData[currentPosition.toInt() + 1].nameMusic,
                    idMusic = allData[currentPosition.toInt() + 1].idMusic,
                    position = allData[currentPosition.toInt() + 1].id!!
                )
            )
        }



       /* if (position != allDatabase.last().id) {
            playerStatePres.updateData(
                data = PlayerEntityModel(
                    nameMusic = allDatabase[position!!.toInt()].nameMusic,
                    idMusic = allDatabase[position.toInt()].idMusic,
                    position = allDatabase[position.toInt()].id!!
                )
            )
        } else {
            playerStatePres.updateData(
                data = PlayerEntityModel(
                    nameMusic = allDatabase[0].nameMusic,
                    idMusic = allDatabase[0].idMusic,
                    position = allDatabase[0].id!!
                )
            )
        }*/


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