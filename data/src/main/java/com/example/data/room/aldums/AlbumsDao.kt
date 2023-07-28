package com.example.data.room.aldums

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapInfo
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(albumsEntity: AlbumsEntity)

    @Query("DELETE FROM albums_entity WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT id, album_map FROM albums_entity WHERE id = :id")
    @MapInfo(keyColumn = "id", valueColumn = "album_map")
    suspend fun searchSong(id: String): Map<String, String>?

}