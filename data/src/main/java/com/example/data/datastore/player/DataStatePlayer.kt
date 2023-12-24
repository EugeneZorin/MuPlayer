package com.example.data.datastore.player

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.data.entity.ConstantEntity.STORY_DATABASE
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.datastory.PlayerStateDt
import com.example.domain.repository.mappers.PlayerDataMapper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStatePlayer @Inject constructor(
    context: Context,
    private val playerMapper: PlayerDataMapper<PlayerData>
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