package com.example.domain.repository.datastory

import com.example.domain.entity.ProtoEntityModel



interface ProtoPlayerContract {
    suspend fun getData(): ProtoEntityModel
    suspend fun saveData(protoEntityModel: ProtoEntityModel)
}