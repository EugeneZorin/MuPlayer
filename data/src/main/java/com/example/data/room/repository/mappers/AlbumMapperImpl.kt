package com.example.data.room.repository.mappers

import com.example.data.room.aldums.AlbumsEntity
import com.example.domain.entity.AlbumEntityModel
import com.example.domain.repository.mappers.AlbumEntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumMapperImpl: AlbumEntityMapper<AlbumsEntity> {
    override fun mapToDomain(entity: AlbumsEntity): AlbumEntityModel {
        return AlbumEntityModel(
            id = entity.id,
            albumList = entity.albumList
        )
    }


}