package com.example.domain.usecase.search

import com.example.domain.entity.CoreEntityModel
import com.example.domain.usecase.contract.UseCaseCoreContract

class UseCaseFindAudio (
    private val findAllAudioFilesContract: FindAllAudioFilesContract,
    private val useCaseCoreContract: UseCaseCoreContract
) : SearchAudioContract {
    override suspend fun searchFileContact(): Boolean {
        findAllAudioFilesContract.findAllAudioFiles().forEach {
            useCaseCoreContract.insertSong(
                CoreEntityModel(
                    nameMusic = it.key,
                    idMusic = it.value
                )
            )
        }
        return true
    }
}
