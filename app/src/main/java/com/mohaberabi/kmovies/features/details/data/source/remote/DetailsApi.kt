package com.mohaberabi.kmovies.features.details.data.source.remote

import com.mohaberabi.kmovies.core.data.source.remote.api.MediaApi
import com.mohaberabi.kmovies.core.data.source.remote.dto.MediaListDto
import com.mohaberabi.kmovies.features.details.data.source.remote.dto.ShowVideosDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsApi {


    @GET("{type}/{id}/{similar}")
    suspend fun getShowSimilar(
        @Path("type") type: String,
        @Path("id") id: Int,
        @Path("page") page: Int = 1,
        @Query("api_key") apiKey: String = MediaApi.API_KEY,
    ): MediaListDto?

    @GET("{type}/{id}/videos")
    suspend fun getShowVideos(
        @Path("type") type: String,
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = MediaApi.API_KEY,
    ): ShowVideosDto?
}