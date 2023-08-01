package com.example.domain.usecase

import com.example.domain.entity.AlbumEntityModel
import com.example.domain.entity.CoreEntityModel
import com.example.domain.repository.CoreContract
import com.example.domain.usecase.contract.SearchFileContact

class SearchFile(
    private val searchFileContact: SearchFileContact,
    private val coreContract: CoreContract
) {
    suspend fun searchFileContact(): Boolean {
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
