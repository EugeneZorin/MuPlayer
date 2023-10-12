package com.example.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.search.SearchAudioContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelPlayer @Inject constructor(
    private val playerStatePres: PlayerStatePres
): ViewModel() {

    private var _playerStatus = MutableLiveData(false)
    val playerStatus: MutableLiveData<Boolean> = _playerStatus


    init {

        viewModelScope.launch {
            playerStatePres.toString()
        }

    }





}