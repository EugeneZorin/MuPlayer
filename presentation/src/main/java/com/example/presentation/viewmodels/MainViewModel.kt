package com.example.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CoreEntityModel
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.room.contract.CoreContractPres
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val  useCaseCoreContract: CoreContractPres,
    private val playerStatePres: PlayerStatePres
): ViewModel() {

    private val _allMusic = MutableLiveData<List<CoreEntityModel>>()
    var allMusic: MutableLiveData<List<CoreEntityModel>> = _allMusic

    private suspend fun getAllMusic(){
        allMusic.value = useCaseCoreContract.getAllCore()
    }

    init {

        viewModelScope.launch {
            getAllMusic()
        }

        viewModelScope.launch {
            val value = playerStatePres.getData()
            Log.d("value_check", "$value")
            delay(6000)
            Log.d("value_check_two", "$value")

        }

        viewModelScope.launch {
            delay(5000)
            /*playerStatePres.updateData(
                data = PlayerEntityModel(
                    time = 12000,
                    nameMusic = "Name",
                    idMusic = "/storage/emulated/0/Download/Overlord III - Opening _ VORACITY (320 kbps).mp3",
                    position = 0
                )
            )*/
            val value = playerStatePres.getData()
            Log.d("check", "$value")
        }


    }


}