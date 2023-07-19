package com.example.data.room.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "core_entity")
data class CoreEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name_music") val nameMusic: String,
    @ColumnInfo(name = "id_music") val idMusic: String
)
