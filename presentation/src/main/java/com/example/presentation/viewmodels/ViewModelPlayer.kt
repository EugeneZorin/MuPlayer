package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.presentation.service.PlayerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelPlayer @Inject constructor(
    private val playerStatePres: PlayerStatePres
): ViewModel() {



    private var _playerStatus = MutableLiveData<List<PlayerEntityModel>>()
    val playerStatus: MutableLiveData<List<PlayerEntityModel>> = _playerStatus

    private val _pauseState = MutableLiveData(false)
    val pauseState: MutableLiveData<Boolean> = _pauseState

    private val _process  = MutableLiveData<Float>()
    val process: MutableLiveData<Float> = _process



    fun updatePlayerState(newState: PlayerEntityModel){
        viewModelScope.launch {
            playerStatePres.updateData(newState)
        }
    }



    init {

        viewModelScope.launch {
            playerStatePres.toString()
        }

    }






}