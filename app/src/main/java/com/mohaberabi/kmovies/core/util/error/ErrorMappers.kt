package com.mohaberabi.kmovies.core.util.error

import android.database.sqlite.SQLiteDiskIOException
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteFullException
import coil.network.HttpException
import kotlinx.coroutines.CancellationException
import java.io.IOException


fun HttpException.toErrorModel(): ErrorModel = ErrorModel(
    errorType = response.code.toRemoteErrorType(),
    cause = cause,
    message = message
)

fun Exception.mapToErrorModel(): ErrorModel {
    val errorType = if (this is CancellationException) {
        throw this
    } else {
        when (this) {
            is IOException -> CommonAppError.IO_FAILURE
            else -> CommonAppError.UNKNOWN
        }
    }
    return ErrorModel(
        errorType = errorType,
        cause = cause,
        message = message
    )
}

fun SQLiteException.toErrorModel(): ErrorModel {
    val type = when (this) {
        is SQLiteFullException -> LocalAppError.DISK_FULL
        is SQLiteDiskIOException -> CommonAppError.IO_FAILURE
        else -> CommonAppError.UNKNOWN
    }
    return ErrorModel(
        errorType = type,
        cause = cause,
        message = message
    )
}