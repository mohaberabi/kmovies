package com.mohaberabi.kmovies.core.util

import com.mohaberabi.kmovies.core.util.error.ErrorModel


sealed class AppResult<out T> {


    data class Done<T>(val data: T) : AppResult<T>()

    data class Error(val error: ErrorModel) : AppResult<Nothing>()
}