package com.example.data.room.playlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums_entity")
data class PlaylistEntity(

    @PrimaryKey() val id: String,

    @ColumnInfo(name = "album_map") val albumList: Map<String, String>?,
)
