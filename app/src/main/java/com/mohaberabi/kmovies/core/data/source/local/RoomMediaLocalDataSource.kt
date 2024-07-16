package com.mohaberabi.kmovies.core.data.source.local

import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteFullException
import com.mohaberabi.kmovies.core.data.source.local.database.dao.MediaDao
import com.mohaberabi.kmovies.core.data.source.mapper.toMedia
import com.mohaberabi.kmovies.core.data.source.mapper.toMediaEntity
import com.mohaberabi.kmovies.core.domain.model.MediaModel
import com.mohaberabi.kmovies.core.domain.source.local.MediaLocalDataSource
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import com.mohaberabi.kmovies.core.util.error.LocalFailure
import com.mohaberabi.kmovies.core.util.error.mapToErrorModel
import com.mohaberabi.kmovies.core.util.error.toErrorModel
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject


class RoomMediaLocalDataSource @Inject constructor(
    private val mediaDao: MediaDao,
    private val dispatchers: DispatchersProvider,
) : MediaLocalDataSource {
    override suspend fun upsertMediaList(medias: List<MediaModel>) {
        withContext(dispatchers.io) {
            try {
                mediaDao.upsertMediaList(medias.map { it.toMediaEntity() })
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }
    }

    override suspend fun upsertMediaItem(media: MediaModel) =
        withContext(dispatchers.io) {
            try {
                mediaDao.upsertMediaItem(media.toMediaEntity())
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }

    override suspend fun getAllWhereIn(ids: Set<Int>): List<MediaModel> {
        return withContext(dispatchers.io) {
            try {
                mediaDao.getAllWhereIn(ids).map { it.toMedia() }
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }
    }

    override suspend fun getAllMediaList(): List<MediaModel> =
        withContext(dispatchers.io) {
            try {
                mediaDao.getAllMediaList().map { it.toMedia() }
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }


    override suspend fun getMediaListByTypeAndCategory(
        mediaType: String,
        category: String
    ): List<MediaModel> {

        return withContext(dispatchers.io) {
            try {
                mediaDao.getMediaListByTypeAndCategory(
                    mediaType = mediaType,
                    category = category,
                ).map { it.toMedia() }
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }
    }


    override suspend fun getMediaListByCategory(
        category: String,
    ): List<MediaModel> {
        return withContext(dispatchers.io) {
            try {
                mediaDao.getMediaListByCategory(category).map { it.toMedia() }

            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }
    }

    override suspend fun getMediaItemById(
        mediaId: Int,
    ): MediaModel {
        return withContext(dispatchers.io) {
            try {
                mediaDao.getMediaItemById(mediaId).toMedia()
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }
    }

    override suspend fun getMediasCount(
        mediaId: Int,
    ): Int {
        return withContext(dispatchers.io) {
            try {
                mediaDao.getMediasCount(mediaId)
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }
    }


    override suspend fun deleteAllMediaItem() {

        return withContext(dispatchers.io) {
            try {
                mediaDao.deleteAllMediaItem()
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }
    }

    override suspend fun deleteAllMediaListByTypeAndCategory(
        mediaType: String,
        category: String,
    ) {
        try {
            mediaDao.deleteAllMediaListByTypeAndCategory(
                mediaType = mediaType,
                category = category,
            )
        } catch (e: SQLiteException) {
            e.printStackTrace()
            throw LocalFailure(e.toErrorModel())
        } catch (e: Exception) {
            e.printStackTrace()
            throw LocalFailure(e.mapToErrorModel())
        }
    }


    override suspend fun deleteAllMediaListByCategory(
        category: String,
    ) {
        try {
            mediaDao.deleteAllMediaListByCategory(category = category)
        } catch (e: SQLiteException) {
            e.printStackTrace()
            throw LocalFailure(e.toErrorModel())
        } catch (e: Exception) {
            e.printStackTrace()
            throw LocalFailure(e.mapToErrorModel())
        }
    }

}