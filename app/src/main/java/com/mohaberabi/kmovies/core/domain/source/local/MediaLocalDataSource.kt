package com.mohaberabi.kmovies.core.domain.source.local

import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.kmovies.core.data.source.local.database.entity.MediaEntity
import com.mohaberabi.kmovies.core.domain.model.MediaModel


interface MediaLocalDataSource {

    suspend fun upsertMediaList(medias: List<MediaModel>)
    suspend fun upsertMediaItem(media: MediaModel)
    suspend fun getAllWhereIn(ids: Set<Int>): List<MediaModel>
    suspend fun getAllMediaList(): List<MediaModel>
    suspend fun getMediaListByTypeAndCategory(
        mediaType: String,
        category: String
    ): List<MediaModel>

    suspend fun getMediaListByCategory(
        category: String
    ): List<MediaModel>

    suspend fun getMediaItemById(
        mediaId: Int
    ): MediaModel

    suspend fun getMediasCount(
        mediaId: Int
    ): Int

    suspend fun deleteAllMediaItem()
    suspend fun deleteAllMediaListByTypeAndCategory(
        mediaType: String,
        category: String
    )

    suspend fun deleteAllMediaListByCategory(
        category: String
    )
    
}