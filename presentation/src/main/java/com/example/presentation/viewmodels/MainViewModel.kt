package com.example.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CoreEntityModel
import com.example.domain.usecase.contract.UseCaseCoreContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val  useCaseCoreContract: UseCaseCoreContract
): ViewModel() {

    private val _allMusic = MutableLiveData<List<CoreEntityModel>>()
    var allMusic: MutableLiveData<List<CoreEntityModel>> = _allMusic


    private suspend fun getAllMusic(){
        allMusic.value = useCaseCoreContract.getAllCore()
        Log.d("allMusic", "${allMusic.value!![0].nameMusic}")
    }

    init {
        viewModelScope.launch {
            getAllMusic()
        }

    }


}