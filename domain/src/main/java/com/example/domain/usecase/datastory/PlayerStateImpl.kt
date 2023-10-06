package com.example.domain.usecase.datastory

import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.datastory.PlayerStateDt
import com.example.domain.usecase.datastory.contract.PlayerStatePres
import java.util.logging.Logger

class PlayerStateImpl (
    private val playerStateDt: PlayerStateDt
): PlayerStatePres {
    override suspend fun getData(): List<PlayerEntityModel> {
        return try {
            playerStateDt.getData()
        } catch (e: Exception){
            throw RuntimeException("Error", e)
        }

    }
}