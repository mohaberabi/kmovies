package com.mohaberabi.kmovies.core.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohaberabi.kmovies.core.data.source.local.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface BookmarksDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: BookmarkEntity)


    @Query("DELETE  FROM bookmarks WHERE mediaId=:id")
    suspend fun removeFromBookmark(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE mediaId =:id)")

    fun isBookmarked(id: Int): Flow<Boolean>

    @Query("SELECT * FROM bookmarks")
    fun getBookmarks(): Flow<List<BookmarkEntity>>
}

