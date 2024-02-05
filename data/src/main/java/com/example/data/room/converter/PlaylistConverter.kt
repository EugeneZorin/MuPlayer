package com.example.data.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PlaylistConverter {

    @TypeConverter
    fun fromString(value: String): Map<String, List<String>> {
        val mapType = object : TypeToken<Map<String, List<String>>>(){}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun toString(map: Map<String, List<String>>): String {
        return Gson().toJson(map)
    }
}

