package com.example.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.data.datastore.mappers.PlayerMapper
import com.example.data.entity.NameDatabase.STORY_DATABASE
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.datastory.PlayerStateDt
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStatePlayer @Inject constructor(
    context: Context,
    private val playerMapper: PlayerMapper
): PlayerStateDt {

    private val Context.playerDataStore: DataStore<PlayerData> by dataStore(
        fileName = STORY_DATABASE,
        serializer = DataPlayerSerializer
    )

    private val listeners = mutableListOf< () -> Unit >()
    private val playerData = context.playerDataStore


    override suspend fun getData(): PlayerEntityModel {
        return playerData.data.map {
            playerMapper.mapToDomain(it)
        }.first()
    }

    override suspend fun updateData(data: PlayerEntityModel) {
        playerData.updateData {
            it.copy(
                nameMusic = data.nameMusic,
                idMusic = data.idMusic,
                position = data.position
            )
        }
        listeners.forEach{
            it.invoke()
        }
    }

    override fun addListener(listener: () -> Unit) {
        listeners.add(listener)
    }

}