package com.example.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CoreEntityModel
import com.example.domain.entity.PlaylistEntityModel
import com.example.domain.usecase.room.contract.CoreContractPres
import com.example.domain.usecase.room.contract.PlaylistContractPres
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelPlayList @Inject constructor(
    private val playlistContractPres: PlaylistContractPres,
    private val useCaseCoreContract: CoreContractPres,
) : ViewModel() {

    private val _isChecked = MutableLiveData(false)
    var isChecked: MutableLiveData<Boolean> = _isChecked

    private val _arrayChosenMusic = mutableStateListOf<Int>()
    val arrayChosenMusic: MutableList<Int> = _arrayChosenMusic

    private var _getNamePlaylist = MutableLiveData<List<String>>()
    val getNamePlaylist: MutableLiveData<List<String>> = _getNamePlaylist

    private var _getPlaylist = mapOf<String, String>()
    val getPlaylist: Map<String, String> = _getPlaylist



    fun createPlaylist(namePlayList: String) {
        viewModelScope.launch {
            playlistContractPres.createPlaylistContract(
                PlaylistEntityModel(
                    id = namePlayList,
                    playlist = transformSelectedElements()
                )
            )
        }
    }

    private suspend fun transformSelectedElements(): Map<String, String> {

        val musicMaps: MutableMap<String, String> = mutableMapOf()

        _arrayChosenMusic.forEach {

            useCaseCoreContract.getMusic(it).forEach { music ->
                musicMaps[music.nameMusic] = music.idMusic
            }

        }

        return musicMaps
    }

     suspend fun getDataPlaylists() {
        _getNamePlaylist.value = playlistContractPres
            .getAllPlaylist().map { it.id }
    }


}