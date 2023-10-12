package com.example.domain.usecase.search

interface FindAllAudioFilesContract {
    fun findAllAudioFiles(): Map<String, String>

}