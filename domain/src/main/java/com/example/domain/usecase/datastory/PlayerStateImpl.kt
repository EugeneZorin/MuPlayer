package com.example.domain.usecase.datastory

import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.datastory.PlayerStateDt
import com.example.domain.usecase.datastory.contract.PlayerStatePres

class PlayerStateImpl (
    private val playerStateDt: PlayerStateDt
): PlayerStatePres {


    override suspend fun getData(): PlayerEntityModel {
        return playerStateDt.getData()
    }

    override suspend fun updateData(data: PlayerEntityModel) {
        playerStateDt.updateData(data)
    }
}