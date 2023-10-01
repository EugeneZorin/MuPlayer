package com.example.data.datastore

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object PlayerSerializer: Serializer<PlayerData> {
    override val defaultValue: PlayerData
        get() = PlayerData()

    override suspend fun readFrom(input: InputStream): PlayerData {
        return try {
            Json.decodeFromString(
                deserializer = PlayerData.serializer(),
                string = input.readBytes().toString()
            )
        } catch (e: SerializationException){
            e.printStackTrace()
            PlayerData()
        }
    }

    override suspend fun writeTo(t: PlayerData, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = PlayerData.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}