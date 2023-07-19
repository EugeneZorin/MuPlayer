package com.example.data.room.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoreEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val nameMusic: String,
    val idMusic: String
)
