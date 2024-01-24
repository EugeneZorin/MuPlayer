package com.example.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PlaylistEntityModel
import com.example.domain.usecase.room.contract.PlaylistContractPres
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelPlayList @Inject constructor(
    private val playlistContractPres: PlaylistContractPres
) : ViewModel() {

    private val _isChecked = MutableLiveData(false)
    var isChecked: MutableLiveData<Boolean> = _isChecked

    private val _arrayChosenMusic = mutableMapOf<String, String>()
    val arrayChosenMusic: MutableMap<String, String> = _arrayChosenMusic

    fun createPlaylist(namePlayList: String){
        viewModelScope.launch {
            if (arrayChosenMusic.isEmpty()){
                playlistContractPres.createPlaylistContract(
                    PlaylistEntityModel(
                        id = namePlayList,
                        playlist = arrayChosenMusic
                    )
                )
            }
        }
    }
}