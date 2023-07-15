package com.example.data.room.aldums

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums_entity")
data class AlbumsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "name_album") val nameAlbum: String,
    @ColumnInfo(name = "name_music") val nameMusic: String,
    @ColumnInfo(name = "id_music") val idMusic: String,
)
