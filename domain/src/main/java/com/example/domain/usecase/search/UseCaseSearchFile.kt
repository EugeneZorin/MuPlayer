package com.example.domain.usecase.search

import com.example.domain.entity.CoreEntityModel
import com.example.domain.repository.CoreContract

class UseCaseSearchFile(
    private val searchFileContact: SearchFileData,
    private val coreContract: CoreContract
): SearchFilePresentation {
    override suspend fun searchFileContact(): Boolean {
        searchFileContact.findAllAudioFiles().forEach {
            coreContract.insert(
                CoreEntityModel(
                    nameMusic = it.key,
                    idMusic = it.value
                )
            )
        }
        return true
    }


}
