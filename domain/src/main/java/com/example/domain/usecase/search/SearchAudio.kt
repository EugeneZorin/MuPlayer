package com.example.domain.usecase.search

import com.example.domain.repository.SearchAudioRepository
import javax.naming.Context
import javax.sound.sampled.AudioFileFormat

class SearchAudio: SearchAudioRepository {
    override fun searchAllAudioFiles(): List<AudioFileFormat> {
        val audioFiles = mutableListOf<String>()

    }
}