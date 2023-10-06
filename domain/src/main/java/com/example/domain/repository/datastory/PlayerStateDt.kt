package com.example.domain.repository.datastory

import com.example.domain.entity.PlayerEntityModel

interface PlayerStateDt {
    suspend fun getData(): List<PlayerEntityModel>

}