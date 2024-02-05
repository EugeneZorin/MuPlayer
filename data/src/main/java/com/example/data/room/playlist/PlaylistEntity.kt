package com.example.data.room.playlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist_entity")
data class PlaylistEntity(

    @PrimaryKey() val id: String,

    @ColumnInfo(name = "playlists") val playList: Map<String, List<String>>?,
)
