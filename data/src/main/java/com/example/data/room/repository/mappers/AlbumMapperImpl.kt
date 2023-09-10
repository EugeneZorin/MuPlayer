package com.example.data.room.repository.mappers

import com.example.data.room.aldums.AlbumsEntity
import com.example.domain.entity.PlaylistEntityModel
import com.example.domain.repository.mappers.PlaylistEntityMapper
import javax.inject.Singleton

@Singleton
class AlbumMapperImpl: PlaylistEntityMapper<AlbumsEntity> {
    override fun mapToDomain(entity: AlbumsEntity): PlaylistEntityModel {
        return PlaylistEntityModel(
            id = entity.id,
            albumList = entity.albumList
        )
    }


}