package com.example.domain.usecase.datastory.contract

import com.example.domain.entity.PlayerEntityModel

interface PlayerStatePres {
    suspend fun getData(): PlayerEntityModel
}