package com.mohaberabi.kmovies.core.data.source.local.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohaberabi.kmovies.core.data.source.mapper.toMedia
import com.mohaberabi.kmovies.core.data.source.mapper.toMediaEntity
import com.mohaberabi.kmovies.core.domain.model.MediaModel


@Entity("bookmarks")
data class BookmarkEntity(
    @Embedded val show: MediaEntity,
    @PrimaryKey(autoGenerate = false)
    val uid: Int = show.mediaId,
)


fun BookmarkEntity.toShow(
): MediaModel = show.toMedia()

fun MediaModel.toBookmarkEntity(
): BookmarkEntity = BookmarkEntity(
    show = toMediaEntity()
)