package com.example.data.room.playlist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapInfo
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlaylistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlistEntity: PlaylistEntity)

    @Query("DELETE FROM playlist_entity WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT id, playlists FROM playlist_entity WHERE id = :id")
    @MapInfo(keyColumn = "id", valueColumn = "playlists")
    suspend fun searchSong(id: String): Map<String, String>?

    @Query("SELECT * FROM playlist_entity")
    suspend fun getAllPlaylist(): List<PlaylistEntity>

}