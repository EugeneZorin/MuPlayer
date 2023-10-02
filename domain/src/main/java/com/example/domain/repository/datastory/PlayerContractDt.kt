package com.example.domain.repository.datastory

import com.example.domain.entity.PlayerEntityModel

interface PlayerContractDt {
    suspend fun getData(): List<PlayerEntityModel>
    suspend fun updateData(data: PlayerEntityModel)

}