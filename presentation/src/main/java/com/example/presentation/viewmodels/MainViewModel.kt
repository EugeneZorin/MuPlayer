package com.example.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CoreEntityModel
import com.example.domain.usecase.datastory.contract.PlayerContractPres
import com.example.domain.usecase.room.contract.CoreContractPres
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val  useCaseCoreContract: CoreContractPres,
    private val  playerContractPres: PlayerContractPres
): ViewModel() {

    private val _allMusic = MutableLiveData<List<CoreEntityModel>>()
    var allMusic: MutableLiveData<List<CoreEntityModel>> = _allMusic


    private suspend fun getAllMusic(){
        allMusic.value = useCaseCoreContract.getAllCore()


    }

    init {
        viewModelScope.launch {
            Log.d("playerContractPres", playerContractPres.getData().toString())
            getAllMusic()

        }


    }


}