package com.example.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CoreEntityModel
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.room.contract.CoreContractPres
import com.example.domain.usecase.search.SearchAudioContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val  useCaseCoreContract: CoreContractPres,
    private val playerStatePres: PlayerStatePres,
    private val searchAudioContract: SearchAudioContract
): ViewModel() {

    private val _allMusic = MutableLiveData<List<CoreEntityModel>>()
    var allMusic: MutableLiveData<List<CoreEntityModel>> = _allMusic

    private suspend fun getAllMusic(){
        _allMusic.value = useCaseCoreContract.getAllCore()
    }

    suspend fun getData(): PlayerEntityModel {
        return playerStatePres.getData()
    }


    /*fun updateData(it: Int){
        viewModelScope.launch {
            playerStatePres.updateData(
                data = PlayerEntityModel(
                    nameMusic = allMusic.value!![it].nameMusic,
                    idMusic = allMusic.value!![it].idMusic,
                    position = 0
                )
            )
        }
    }*/



    init {



        viewModelScope.launch {

            val set = searchAudioContract.searchFileContact()
            Log.d("SSSSSSS","$set")

            getAllMusic()

            val value = playerStatePres.getData()
            Log.d("value_check", "$value")
        }

        /*viewModelScope.launch {
            val value = playerStatePres.getData()
            Log.d("value_check", "$value")
            delay(6000)
            Log.d("value_check_two", "$value")

        }

        viewModelScope.launch {
            delay(5000)
            playerStatePres.updateData(
                data = PlayerEntityModel(
                    time = 12000,
                    nameMusic = "Name",
                    idMusic = "/storage/emulated/0/Download/Overlord III - Opening _ VORACITY (320 kbps).mp3",
                    position = 0
                )
            )
            val value = playerStatePres.getData()
            Log.d("check", "$value")
        }*/


    }


}