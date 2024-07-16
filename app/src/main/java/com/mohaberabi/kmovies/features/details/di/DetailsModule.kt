package com.mohaberabi.kmovies.features.details.di

import com.mohaberabi.kmovies.core.data.source.local.database.db.MediaDatabase
import com.mohaberabi.kmovies.core.data.source.remote.api.MediaApi
import com.mohaberabi.kmovies.core.di.ApplicationScope
import com.mohaberabi.kmovies.core.domain.source.local.MediaLocalDataSource
import com.mohaberabi.kmovies.core.util.DefaultDispatchersProvider
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import com.mohaberabi.kmovies.features.details.data.repository.DefaultDetailsRepository
import com.mohaberabi.kmovies.features.details.data.source.local.RoomShowVideoLocalDataSource
import com.mohaberabi.kmovies.features.details.data.source.remote.DetailsApi
import com.mohaberabi.kmovies.features.details.data.source.remote.RetrofitDetailsRemoteDataSource
import com.mohaberabi.kmovies.features.details.domain.repository.ShowDetailsRepository
import com.mohaberabi.kmovies.features.details.domain.source.local.ShowVideoLocalDatasource
import com.mohaberabi.kmovies.features.details.domain.source.remote.ShowDetailsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DetailsModule {

    @Provides
    @Singleton
    fun provideDetailsApi(
        convertor: Converter.Factory,
        client: OkHttpClient,
    ): DetailsApi = Retrofit.Builder()
        .baseUrl(MediaApi.BASE_URL).client(client)
        .addConverterFactory(convertor)
        .build()
        .create(DetailsApi::class.java)


    @Provides
    @Singleton
    fun provideDetailLocalDataSource(
        dispatchers: DispatchersProvider,
        mediaDatabase: MediaDatabase,
    ): ShowVideoLocalDatasource = RoomShowVideoLocalDataSource(
        dispatchers = dispatchers,
        showVideoDao = mediaDatabase.showVideosDao()
    )

    @Provides
    @Singleton
    fun provideDetailsRemoteDataSource(
        api: DetailsApi,
        dispatchers: DispatchersProvider,
    ): ShowDetailsRemoteDataSource =
        RetrofitDetailsRemoteDataSource(
            detailsApi = api,
            dispatchers = dispatchers,
        )

    @Provides
    @Singleton
    fun provideDetailRepository(
        showsLocalDataSource: MediaLocalDataSource,
        showDetailRemoteDatasource: ShowDetailsRemoteDataSource,
        @ApplicationScope appScope: CoroutineScope,
        showVideoLocalDataSource: ShowVideoLocalDatasource,
    ): ShowDetailsRepository = DefaultDetailsRepository(
        appScope = appScope,
        showDetailRemoteDatasource = showDetailRemoteDatasource,
        showsLocalDataSource = showsLocalDataSource,
        showVideoLocalDataSource = showVideoLocalDataSource
    )
}