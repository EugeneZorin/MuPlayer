package com.example.data.datastore.external

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.datastore.mappers.ExternalPlayerMapper
import com.example.domain.entity.PlayerExternalModel
import com.example.domain.repository.datastory.PlayerExternalDt
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ExternalPlayerStates @Inject constructor(
    val context: Context,
    private val externalPlayerMapper: ExternalPlayerMapper
): PlayerExternalDt {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(EXTERNAL_PLAYER)
    private val playerData = context.dataStore
    override suspend fun saveData(externalPlayerData: PlayerExternalModel) {
        playerData.edit { data ->
            data[booleanPreferencesKey(PAUSE_STOP)] = externalPlayerData.pauseStop
            data[longPreferencesKey(POSITION)] = externalPlayerData.position
        }
    }


    override suspend fun getData(): PlayerExternalModel {
        return playerData.data.map {
            externalPlayerMapper.mapToDomain(
                ExternalPlayerData(
                    it[booleanPreferencesKey(PAUSE_STOP)] ?: false,
                    it[longPreferencesKey(POSITION)] ?: 0
                )

            )
        }.first()
    }


    companion object{
        const val EXTERNAL_PLAYER = "external_player"
        const val PAUSE_STOP = "pause_stop"
        const val POSITION = "position"
    }


}