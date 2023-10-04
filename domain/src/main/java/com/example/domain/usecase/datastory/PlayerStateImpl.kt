package com.example.domain.usecase.datastory

import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.datastory.PlayerStateDt
import com.example.domain.usecase.datastory.contract.PlayerStatePres

class PlayerStateImpl (
    private val playerStateContract: PlayerStateDt<PlayerEntityModel>
): PlayerStatePres {
    override suspend fun getData(): List<PlayerEntityModel>{
        return playerStateContract.getData()
    }
}