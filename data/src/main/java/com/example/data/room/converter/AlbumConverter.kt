package com.example.data.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AlbumConverter {

    @TypeConverter
    fun fromMapWrapper(mapWrapper: MapWrapper): String {
        return Gson().toJson(mapWrapper.map)
    }

    @TypeConverter
    fun toMapWrapper(json: String): MapWrapper {
        val mapWrapper = MapWrapper()
        mapWrapper.map = Gson().fromJson(json, object : TypeToken<Map<String, String>>() {}.type)
        return mapWrapper
    }
}

class MapWrapper {
    var map: Map<String, String> = emptyMap()
}