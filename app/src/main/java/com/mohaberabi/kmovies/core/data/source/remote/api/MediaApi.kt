package com.mohaberabi.kmovies.core.data.source.remote.api

import com.mohaberabi.kmovies.BuildConfig
import com.mohaberabi.kmovies.core.data.source.remote.dto.MediaListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaApi {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = BuildConfig.API_KEY

    }


    @GET("search/multi")
    suspend fun searchShows(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): MediaListDto?


    @GET("trending/{type}/{time}")
    suspend fun getAllTrending(
        @Path("type") type: String,
        @Path("time") time: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): MediaListDto?

    @GET("{type}/{category}")
    suspend fun getShows(
        @Path("category") category: String,
        @Path("type") type: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): MediaListDto?
}