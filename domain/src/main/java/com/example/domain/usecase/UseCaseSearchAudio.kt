package com.example.domain.usecase

import com.example.domain.entity.CoreEntityModel
import com.example.domain.usecase.contract.room.CoreContractPres
import com.example.domain.usecase.search.FindAllAudioFilesContract
import com.example.domain.usecase.search.SearchAudioContract

class UseCaseSearchAudio (
    private val findAllAudioFilesContract: FindAllAudioFilesContract,
    private val useCaseCoreContract: CoreContractPres
) : SearchAudioContract {
    override suspend fun searchFileContact(): Boolean {
        val audioFiles = findAllAudioFilesContract.findAllAudioFiles()
        return if (audioFiles.isEmpty()){
            findAllAudioFilesContract.findAllAudioFiles().forEach {
                useCaseCoreContract.insertSong(
                    CoreEntityModel(
                        nameMusic = it.key,
                        idMusic = it.value
                    )
                )
            }
            true
        } else {
            false
        }
    }
}
