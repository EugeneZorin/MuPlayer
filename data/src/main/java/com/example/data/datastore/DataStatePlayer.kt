package com.example.data.datastore

import android.content.Context
import androidx.datastore.dataStore
import com.example.data.datastore.mappers.PlayerMapper
import com.example.domain.entity.PlayerEntityModel
import com.example.domain.repository.datastory.PlayerStateDt
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject


class DataStatePlayer @Inject constructor(
    private val context: Context,
    private val playerMapper: PlayerMapper
): PlayerStateDt {


    private val Context.dataStore by dataStore("player_data", DataPlayerSerializer)
    override suspend fun getData(): List<PlayerEntityModel> {
        return context.dataStore.data.map {
            playerMapper.mapToDomain(it)
        }.toList()
    }

}