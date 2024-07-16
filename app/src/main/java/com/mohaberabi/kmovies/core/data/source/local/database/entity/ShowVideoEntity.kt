package com.mohaberabi.kmovies.core.data.source.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohaberabi.kmovies.features.details.domain.model.ShowVideoSiteModel


@Entity(tableName = "showVideo")
data class ShowVideoEntity(

    @PrimaryKey(autoGenerate = false)
    val showId: String,
    val key: String? = null,
    val site: String? = null
)

fun ShowVideoSiteModel.toShowEntity(
    showId: String,
): ShowVideoEntity = ShowVideoEntity(
    key = key,
    site = site,
    showId = showId,
)

fun ShowVideoEntity.toShowVideo(
): ShowVideoSiteModel = ShowVideoSiteModel(
    key = key,
    site = site
)