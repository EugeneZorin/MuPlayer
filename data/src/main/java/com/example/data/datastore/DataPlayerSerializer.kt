package com.example.data.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


object DataPlayerSerializer: Serializer<PlayerData> {

    override val defaultValue: PlayerData = PlayerData()

    override suspend fun readFrom(input: InputStream): PlayerData {
        try {
            return Json.decodeFromString(
                PlayerData.serializer(), input.readBytes().decodeToString()
            )
        } catch (e: SerializationException){
            throw CorruptionException("Error", e)
        }
    }

    override suspend fun writeTo(t: PlayerData, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(PlayerData.serializer(), t).encodeToByteArray()
            )
        }
    }


}