package com.example.data.room.repository.mappers

import com.example.data.room.playlist.PlaylistEntity
import com.example.domain.entity.PlaylistEntityModel
import com.example.domain.repository.mappers.PlaylistEntityMapper
import javax.inject.Singleton

@Singleton
class PlaylistMapperImpl: PlaylistEntityMapper<PlaylistEntity> {
    override fun mapToDomain(entity: PlaylistEntity): PlaylistEntityModel {
        return PlaylistEntityModel(
            id = entity.id,
            albumList = entity.albumList
        )
    }


}