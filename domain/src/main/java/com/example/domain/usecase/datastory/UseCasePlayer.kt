package com.example.domain.usecase.datastory

import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.datastory.PlayerContractDt
import com.example.domain.usecase.datastory.contract.PlayerContractPres



class UseCasePlayer(
    private val playerContractDt: PlayerContractDt
): PlayerContractPres {
    override suspend fun getData(): List<PlayerEntityModel> {
        return playerContractDt.getData()
    }

    override suspend fun updateData(data: PlayerEntityModel) {
        playerContractDt.updateData(data)
    }
}