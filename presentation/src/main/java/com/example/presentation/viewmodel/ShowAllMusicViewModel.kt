package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.entity.CoreEntityModel
import com.example.domain.usecase.contract.UseCaseCoreContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowAllMusicViewModel @Inject constructor(
    private val useCaseCoreContract: UseCaseCoreContract
): ViewModel() {

    private var allMusic = mutableListOf<CoreEntityModel>()
    suspend fun showAllMusic(){
        allMusic = useCaseCoreContract.getAllCore().toMutableList()
    }

}
