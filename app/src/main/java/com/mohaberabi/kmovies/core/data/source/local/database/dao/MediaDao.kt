package com.mohaberabi.kmovies.core.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.kmovies.core.data.source.local.database.entity.MediaEntity


@Dao
interface MediaDao {

    @Upsert
    suspend fun upsertMediaList(mediaEntities: List<MediaEntity>)

    @Upsert
    suspend fun upsertMediaItem(mediaEntity: MediaEntity)


    @Query("SELECT * FROM medias")
    suspend fun getAllMediaList(): List<MediaEntity>

    @Query("SELECT * FROM medias WHERE mediaType = :mediaType AND category = :category")
    suspend fun getMediaListByTypeAndCategory(
        mediaType: String, category: String
    ): List<MediaEntity>

    @Query("SELECT * FROM medias WHERE category = :category")
    suspend fun getMediaListByCategory(
        category: String
    ): List<MediaEntity>

    @Query("SELECT * FROM medias WHERE mediaId = :mediaId")
    suspend fun getMediaItemById(
        mediaId: Int
    ): MediaEntity

    @Query("SELECT COUNT(*) FROM medias WHERE mediaId = :mediaId")
    suspend fun getMediasCount(
        mediaId: Int
    ): Int


    @Query("DELETE FROM medias")
    suspend fun deleteAllMediaItem()

    @Query("DELETE FROM medias WHERE mediaType = :mediaType AND category = :category")
    suspend fun deleteAllMediaListByTypeAndCategory(
        mediaType: String,
        category: String
    )

    @Query("DELETE FROM medias WHERE category = :category")
    suspend fun deleteAllMediaListByCategory(
        category: String
    )


    @Query("SELECT * from medias WHERE mediaId IN (:ids)")
    suspend fun getAllWhereIn(ids: Set<Int>): List<MediaEntity>
}