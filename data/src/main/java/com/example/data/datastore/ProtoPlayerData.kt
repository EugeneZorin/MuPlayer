package com.example.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.dataStore
import com.example.domain.repository.datastory.ProtoPlayerContract
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerializationException
import javax.inject.Inject


class ProtoPlayerData @Inject constructor(
    private val context: Context
){


    private val Context.protoPlayerData by dataStore("dataPlayer", PlayerSerializer)
    suspend fun getData(): Flow<PlayerData> {
        return context.protoPlayerData.data
    }

    suspend fun saveData(playerData: PlayerData){
        try {
            context.protoPlayerData.updateData {
                it.copy(
                    time = playerData.time,
                    nameMusic = playerData.nameMusic,
                    idMusic = playerData.idMusic,
                    position = playerData.position
                )
            }
        } catch (e: SerializationException) {
            Log.d("ErrorSaveDataPlayer", "$e")
        }

    }

}


