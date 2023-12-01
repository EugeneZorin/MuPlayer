package com.example.domain.repository.smusic

interface MusicSwitchPres {
    suspend fun nextMusic()
    suspend fun backMusic()
}