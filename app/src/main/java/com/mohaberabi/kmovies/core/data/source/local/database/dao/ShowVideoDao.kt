package com.mohaberabi.kmovies.core.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.kmovies.core.data.source.local.database.entity.ShowVideoEntity
import com.mohaberabi.kmovies.features.details.domain.model.ShowVideoSiteModel


@Dao
interface ShowVideoDao {

    @Query("SELECT * from showVideo WHERE showId=:showId")
    suspend fun getShowVideo(showId: String): ShowVideoEntity?

    @Upsert
    suspend fun addShowVideo(video: ShowVideoEntity)


}