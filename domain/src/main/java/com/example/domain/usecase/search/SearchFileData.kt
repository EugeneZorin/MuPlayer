package com.example.domain.usecase.search

interface SearchFileData {
    fun findAllAudioFiles(): Map<String, String>
}