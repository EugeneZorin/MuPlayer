package com.example.presentation.viewmodels

import android.app.Application
import android.content.Intent
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CoreEntityModel
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.entity.PlayerExternalModel
import com.example.domain.repository.preferences.FirstRunPres
import com.example.domain.usecase.datastory.contract.ExternalPlayerPres
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.room.contract.CoreContractPres
import com.example.domain.usecase.search.SearchAudioContract
import com.example.presentation.service.PlayerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCaseCoreContract: CoreContractPres,
    private val playerStatePres: PlayerStatePres,
    private val searchAudioContract: SearchAudioContract,
    private val firstRunPres: FirstRunPres,
    private val externalPlayerPres: ExternalPlayerPres,
) : ViewModel() {

    private val _allMusic = MutableLiveData<List<CoreEntityModel>>()
    var allMusic: MutableLiveData<List<CoreEntityModel>> = _allMusic

    private var _getData = MutableLiveData<PlayerEntityModel>()
    val getData: MutableLiveData<PlayerEntityModel> = _getData

    private val _externalPlayer = MutableLiveData<PlayerExternalModel>()
    val externalPlayer: MutableLiveData<PlayerExternalModel> = _externalPlayer


    fun updateExternalData(state: Boolean){
        viewModelScope.launch {
            externalPlayerPres.updatePauseStop(
                externalPlayerData = PlayerExternalModel(
                    pauseStop = state
                )
            )
        }
    }

    init {
        externalPlayerPres.addListener {
            viewModelScope.launch {
                externalPlayer.value = externalPlayerPres.getData()
            }
        }
    }

    init {
        playerStatePres.addListener {
            viewModelScope.launch {
                _getData.value = playerStatePres.getData()
            }
        }
    }

    private suspend fun getAllMusic() {
        _allMusic.value = useCaseCoreContract.getAllCore()
    }

    suspend fun updateData(it: Int) = withContext(
        viewModelScope.coroutineContext
    ) {

        playerStatePres.updateData(
            data = PlayerEntityModel(
                nameMusic = allMusic.value!![it].nameMusic,
                idMusic = allMusic.value!![it].idMusic,
                position = allMusic.value!![it].id!!
            )
        )

        _getData.value = playerStatePres.getData()

    }


    init {
        viewModelScope.launch {
            getAllMusic()
        }
    }

    // Check first run
    suspend fun firstRun() {
        // Search for audio files on the device
        if (firstRunPres.isFirstRun()) {
            searchAudioContract.searchFileContact()
        }
        firstRunPres.setFirstRun(false)
    }


    // Permission bloc

    private val _permissions = mutableStateListOf<String>()
    val permissions = _permissions

    fun dismissDialog() {
        permissions.removeFirst()
    }

    // Add new permission
    fun onPermissionGet(
        permission: String,
        granted: Boolean,
    ) {
        if (!granted && !permissions.contains(permission)) {
            permissions.add(permission)
        }
    }


}