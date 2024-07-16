package com.mohaberabi.kmovies.core.data.source.local.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohaberabi.kmovies.core.data.source.local.database.convertor.MediaTypeCovnertors
import com.mohaberabi.kmovies.core.data.source.local.database.dao.BookmarksDao
import com.mohaberabi.kmovies.core.data.source.local.database.dao.MediaDao
import com.mohaberabi.kmovies.core.data.source.local.database.dao.ShowSearchDao
import com.mohaberabi.kmovies.core.data.source.local.database.dao.ShowVideoDao
import com.mohaberabi.kmovies.core.data.source.local.database.entity.BookmarkEntity
import com.mohaberabi.kmovies.core.data.source.local.database.entity.MediaEntity
import com.mohaberabi.kmovies.core.data.source.local.database.entity.ShowSearchEntity
import com.mohaberabi.kmovies.core.data.source.local.database.entity.ShowVideoEntity


@Database(
    version = 1,
    entities = [
        MediaEntity::class,
        ShowVideoEntity::class,
        ShowSearchEntity::class,
        BookmarkEntity::class
    ],
)
@TypeConverters(
    MediaTypeCovnertors::class
)
abstract class MediaDatabase : RoomDatabase() {
    abstract fun mediaDao(): MediaDao
    abstract fun showVideosDao(): ShowVideoDao
    abstract fun searchDao(): ShowSearchDao
    abstract fun bookmarksDao(): BookmarksDao
}