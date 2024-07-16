package com.mohaberabi.kmovies.core.util.error

abstract class AppThrowable(
    open val error: ErrorModel,
) : Throwable(
    message = error.message,
    cause = error.cause
)

data class LocalFailure(
    override val error: ErrorModel,
) : AppThrowable(
    error = error
)


data class RemoteFailure(
    override val error: ErrorModel,
) : AppThrowable(
    error = error
)

data class CommonFailure(
    override val error: ErrorModel,
) : AppThrowable(
    error = error
)

data class ErrorModel(
    val errorType: AppError,
    val message: String? = null,
    val code: Int? = null,
    val cause: Throwable? = null,
)