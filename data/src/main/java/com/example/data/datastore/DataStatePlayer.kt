package com.example.data.datastore

import android.content.Context
import androidx.datastore.dataStore
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.datastory.PlayerContractDt
import com.example.domain.repository.mappers.PlayerDataEntityMapper
import kotlinx.coroutines.flow.toList
import javax.inject.Inject


class DataStatePlayer @Inject constructor(
    private val context: Context,
    private val playerDataMapperImpl: PlayerDataEntityMapper<PlayerData>
): PlayerContractDt {

    private val Context.dataStore by dataStore("player_data", DataPlayerSerializer)

    override suspend fun getData(): List<PlayerEntityModel> {
        val test = context.dataStore.data.toList()
        return test.map {
            playerDataMapperImpl.mapToDomain(it)
        }
    }

    override suspend fun updateData(data: PlayerEntityModel) {
        context.dataStore.updateData {
            it.copy(
                time = data.time
            )
        }
    }



}