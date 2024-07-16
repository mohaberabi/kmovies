package com.mohaberabi.kmovies.features.details.data.source.local

import android.database.sqlite.SQLiteException
import com.mohaberabi.kmovies.core.data.source.local.database.dao.ShowVideoDao
import com.mohaberabi.kmovies.core.data.source.local.database.entity.toShowEntity
import com.mohaberabi.kmovies.core.data.source.local.database.entity.toShowVideo
import com.mohaberabi.kmovies.core.util.DispatchersProvider
import com.mohaberabi.kmovies.core.util.error.LocalFailure
import com.mohaberabi.kmovies.core.util.error.mapToErrorModel
import com.mohaberabi.kmovies.core.util.error.toErrorModel
import com.mohaberabi.kmovies.features.details.domain.model.ShowVideoSiteModel
import com.mohaberabi.kmovies.features.details.domain.source.local.ShowVideoLocalDatasource
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RoomShowVideoLocalDataSource @Inject constructor(
    private val showVideoDao: ShowVideoDao,
    private val dispatchers: DispatchersProvider,
) : ShowVideoLocalDatasource {
    override suspend fun getShowVideo(
        showId: String,
    ): ShowVideoSiteModel? {
        return withContext(dispatchers.io) {
            try {
                showVideoDao.getShowVideo(showId)?.toShowVideo()
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }

    }

    override suspend fun addShowVideo(
        video: ShowVideoSiteModel,
        showId: String,
    ) {
        withContext(dispatchers.io) {
            try {
                showVideoDao.addShowVideo(video.toShowEntity(showId))
            } catch (e: SQLiteException) {
                e.printStackTrace()
                throw LocalFailure(e.toErrorModel())
            } catch (e: Exception) {
                e.printStackTrace()
                throw LocalFailure(e.mapToErrorModel())
            }
        }

    }
}