package com.example.domain.usecase.contract

interface SearchFileContact {
    fun findAllAudioFiles(): Map<String, String>
}