package com.example.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toCollection
import javax.inject.Inject


class DataStatePlayer @Inject constructor(
    private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("PLAYER_DATA")

    suspend fun getData(): String {
        return context.dataStore.data.toString()
    }


}