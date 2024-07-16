package com.mohaberabi.kmovies.core.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.kmovies.core.data.source.local.database.entity.ShowSearchEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ShowSearchDao {


    @Query("SELECT id FROM showSearch WHERE showSearch MATCH :query")

    fun searchShows(query: String): Flow<List<Int>>

    @Upsert
    suspend fun insertSearchShow(searchShows: List<ShowSearchEntity>)


}