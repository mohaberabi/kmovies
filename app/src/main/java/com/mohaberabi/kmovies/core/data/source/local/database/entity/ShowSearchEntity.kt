package com.mohaberabi.kmovies.core.data.source.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import com.mohaberabi.kmovies.features.search.domain.model.ShowSearchModel

@Entity(tableName = "showSearch")
@Fts4
data class ShowSearchEntity(
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("title")
    val title: String,
)


fun ShowSearchModel.toEntity(
): ShowSearchEntity = ShowSearchEntity(
    id = id,
    title = title
)