package com.example.domain.usecase.datastory.contract

import com.example.domain.entity.PlayerEntityModel

interface PlayerContractPres {
    suspend fun getData(): List<PlayerEntityModel>
    suspend fun updateData(data: PlayerEntityModel)

}