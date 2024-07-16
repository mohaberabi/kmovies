package com.mohaberabi.kmovies.core.util.error


interface AppError


enum class LocalAppError : AppError {
    CONSTRAINS_FAILURE,
    DISK_FULL,
    INVALID_QUERY,
    STALE_DATA,
}

enum class CommonAppError : AppError {
    IO_FAILURE,
    UNKNOWN,
}

enum class RemoteAppError : AppError {
    UNKNOWN,
    SOCKET_TIME_OUT,
    UNKNOWN_HOST,
    CONNECTION_ERROR,
    SSL_ERROR,
    SERIALIZATION_EXCEPTION,
    BAD_REQUEST,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    METHOD_NOT_ALLOWED,
    CONFLICT,
    UNSUPPORTED_MEDIA,
    INTERNAL_SERVER_ERROR,
    BAD_GATEWAY,
    TIMEOUT,
    NOT_IMPLEMENTED,
    SERVICE_UNAVAILABLE,
    GATEWAY_TIMEOUT
}