package com.mohaberabi.kmovies.core.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mohaberabi.kmovies.core.data.source.local.database.convertor.MediaTypeCovnertors
import com.mohaberabi.kmovies.core.data.source.local.database.db.MediaDatabase
import com.mohaberabi.kmovies.core.util.DefaultDispatchersProvider
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideConvertorFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return json.asConverterFactory(contentType)
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor).build()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun provideDispatchersProvider(
    ): DispatchersProvider = DefaultDispatchersProvider()

    @Singleton
    @Provides
    @ApplicationScope
    fun provideAppSuperVisorScope(
    ): CoroutineScope = CoroutineScope(SupervisorJob())

    @Singleton
    @Provides

    fun provideMediaDatabase(
        @ApplicationContext context: Context,
    ): MediaDatabase = Room.databaseBuilder(
        context,
        MediaDatabase::class.java,
        "media.db"
    ).addTypeConverter(MediaTypeCovnertors())
        .build()

}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationScope