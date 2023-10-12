package com.example.domain.usecase

import com.example.domain.entity.CoreEntityModel
import com.example.domain.usecase.room.contract.CoreContractPres
import com.example.domain.usecase.search.FindAllAudioFilesContract
import com.example.domain.usecase.search.SearchAudioContract

class SearchAudio (
    private val findAllAudioFilesContract: FindAllAudioFilesContract,
    private val useCaseCoreContract: CoreContractPres
) : SearchAudioContract {
    override suspend fun searchFileContact() {
        try {
            findAllAudioFilesContract.findAllAudioFiles().forEach {
                useCaseCoreContract.insertSong(
                    CoreEntityModel(
                        nameMusic = it.key,
                        idMusic = it.value
                    )
                )
            }
        } catch (exception: Exception){
            throw exception
        }
    }
}
