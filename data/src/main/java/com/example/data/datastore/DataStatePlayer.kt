package com.example.data.datastore

import android.content.Context
import androidx.datastore.dataStore
import com.example.domain.repository.datastory.PlayerStateDt
import kotlinx.coroutines.flow.toList
import javax.inject.Inject


class DataStatePlayer @Inject constructor(
    private val context: Context
): PlayerStateDt<PlayerData> {

    private val Context.dataStore by dataStore("player_data", DataPlayerSerializer)
    override suspend fun getData(): List<PlayerData> {
        return context.dataStore.data.toList()
    }

}