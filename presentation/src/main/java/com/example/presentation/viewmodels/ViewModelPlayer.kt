package com.example.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelPlayer @Inject constructor(
    private val playerStatePres: PlayerStatePres
): ViewModel() {

    private var _playerStatus = MutableLiveData(false)
    val playerStatus: MutableLiveData<Boolean> = _playerStatus


    suspend fun getData(): PlayerEntityModel {
        return playerStatePres.getData()
    }



}