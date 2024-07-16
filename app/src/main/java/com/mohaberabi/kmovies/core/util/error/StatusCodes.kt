package com.mohaberabi.kmovies.core.util.error


fun Int.toRemoteErrorType(): RemoteAppError {
    return when (this) {
        400 -> RemoteAppError.BAD_REQUEST
        401 -> RemoteAppError.UNAUTHORIZED
        403 -> RemoteAppError.FORBIDDEN
        404 -> RemoteAppError.NOT_FOUND
        405 -> RemoteAppError.METHOD_NOT_ALLOWED
        409 -> RemoteAppError.CONFLICT
        415 -> RemoteAppError.UNSUPPORTED_MEDIA
        500 -> RemoteAppError.INTERNAL_SERVER_ERROR
        501 -> RemoteAppError.NOT_IMPLEMENTED
        502 -> RemoteAppError.BAD_GATEWAY
        503 -> RemoteAppError.SERVICE_UNAVAILABLE
        504 -> RemoteAppError.GATEWAY_TIMEOUT
        408 -> RemoteAppError.TIMEOUT
        else -> RemoteAppError.UNKNOWN
    }
}
