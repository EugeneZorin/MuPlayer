package com.example.data.datastore.external

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.entity.ConstantEntity.EXTERNAL_PLAYER
import com.example.data.entity.ConstantEntity.PAUSE_STOP
import com.example.data.entity.ConstantEntity.POSITION
import com.example.domain.entity.PlayerExternalModel
import com.example.domain.repository.datastory.PlayerExternalDt
import com.example.domain.repository.mappers.ExternalDataMapper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ExternalPlayerStates @Inject constructor(
    val context: Context,
    private val externalPlayerMapper: ExternalDataMapper<ExternalPlayerData>
): PlayerExternalDt {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(EXTERNAL_PLAYER)
    private val playerData = context.dataStore
    private val listeners = mutableListOf< () -> Unit >()

    // Saves the player states to the database
    override suspend fun updatePauseStop(externalPlayerData: PlayerExternalModel) {
        playerData.edit { pauseStop ->
            pauseStop[booleanPreferencesKey(PAUSE_STOP)] = externalPlayerData.pauseStop
        }
        listeners.forEach{
            it.invoke()
        }
    }

    // Saves how much time has passed since the beginning of the track
    override suspend fun updatePosition(externalPlayerData: PlayerExternalModel) {
        playerData.edit { position ->
            position[longPreferencesKey(POSITION)] = externalPlayerData.position
        }
    }


    // To get the stored values
    override suspend fun getData(): PlayerExternalModel {
        return playerData.data.map {
            externalPlayerMapper.mapToDomain(
                ExternalPlayerData(
                    pauseStop = it[booleanPreferencesKey(PAUSE_STOP)] ?: false,
                    position = it[longPreferencesKey(POSITION)] ?: 0
                )

            )
        }.first()
    }

    override fun addListener(listener: () -> Unit) {
        listeners.add(listener)
    }



}