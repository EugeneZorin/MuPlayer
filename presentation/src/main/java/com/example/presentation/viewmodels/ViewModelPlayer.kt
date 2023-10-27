package com.example.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.datastory.contract.PlayerStatePres
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