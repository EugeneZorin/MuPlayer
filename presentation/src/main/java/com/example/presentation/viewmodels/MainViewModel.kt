package com.example.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CoreEntityModel
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.entity.PlayerExternalModel
import com.example.domain.entity.PlaylistEntityModel
import com.example.domain.repository.preferences.FirstRunPres
import com.example.domain.usecase.datastory.contract.ExternalPlayerPres
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import com.example.domain.usecase.room.contract.CoreContractPres
import com.example.domain.usecase.room.contract.PlaylistContractPres
import com.example.domain.usecase.search.SearchAudioContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCaseCoreContract: CoreContractPres,
    private val playlistContractPres: PlaylistContractPres,
    private val playerStatePres: PlayerStatePres,
    private val searchAudioContract: SearchAudioContract,
    private val firstRunPres: FirstRunPres,
    private val externalPlayerPres: ExternalPlayerPres,
) : ViewModel() {

    private val _allMusicMain = MutableLiveData<List<CoreEntityModel>>()
    var allMusicMain: MutableLiveData<List<CoreEntityModel>> = _allMusicMain

    private val _allMusicPlaylist = MutableLiveData<List<PlaylistEntityModel>>()
    var allMusicPlaylist: MutableLiveData<List<PlaylistEntityModel>> = _allMusicPlaylist

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


    suspend fun updateData(it: Int, authenticator: String) = withContext(
        viewModelScope.coroutineContext
    ) {

        when (authenticator) {
           /* main_screen ->
                playerStatePres.updateData(
                    data = PlayerEntityModel(
                        nameMusic = _allMusicMain.value!![it].nameMusic,
                        idMusic = _allMusicMain.value!![it].idMusic,
                        position = _allMusicMain.value!![it].id!!
                    )
                )
            player_screen ->
                playerStatePres.updateData(
                    data = PlayerEntityModel(
                        nameMusic = _allMusicPlaylist.value!![it].id,
                        idMusic = _allMusicPlaylist.value!![it].playlist[it],
                    )
                )*/

        }


        _getData.value = playerStatePres.getData()

    }



    suspend fun getAllMusic() {
        _allMusicMain.value = useCaseCoreContract.getAllCore()
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