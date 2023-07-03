package com.example.domain.repository

import javax.sound.sampled.AudioFileFormat

interface SearchAudioRepository {
    fun searchAllAudioFiles(): List<AudioFileFormat>
}