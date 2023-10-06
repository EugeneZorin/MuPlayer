package com.example.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.data.datastore.mappers.PlayerMapper
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.datastory.PlayerStateDt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.serializer
import java.util.logging.Logger
import javax.inject.Inject


class DataStatePlayer @Inject constructor(
    private val context: Context,
    private val playerMapper: PlayerMapper
): PlayerStateDt {

    private val Context.playerDataStore: DataStore<PlayerData> by dataStore(
        fileName = "player_data.pb",
        serializer = DataPlayerSerializer
    )

    private fun Context.readPlayerData(): Flow<PlayerData> {
        val flow = playerDataStore.data
        Logger.getLogger("123")
        flow.onEach { data ->
            Log.d("DataStatePlayer", "Player Data: $data")
        }.launchIn(GlobalScope)
        return flow
    }



    private suspend fun <T> flowToList(flow: Flow<T>): List<T> {
        return flow.toList()
    }

    override suspend fun getData(): List<PlayerEntityModel> {
        return try {
            withContext(Dispatchers.IO) {
                val playerData = context.readPlayerData()
                val playerEntityList = playerData.map { playerMapper.mapToDomain(it) }
                flowToList(playerEntityList)
            }
        } catch (e: Exception) {
            Log.e("DataStatePlayer", "Error in getData", e)
            emptyList()
        }
    }

}