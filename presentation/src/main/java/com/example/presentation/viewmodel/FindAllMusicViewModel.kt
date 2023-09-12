package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.search.FindAllAudioFilesContract
import com.example.domain.usecase.search.SearchAudioContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindAllMusicViewModel @Inject constructor(
    private val findAllAudioFilesContract: FindAllAudioFilesContract,
): ViewModel(){

    private val allMusic = mutableListOf(findAllAudioFilesContract.findAllAudioFiles().size)
    fun executed(): MutableList<Int> {
        return allMusic
    }
}