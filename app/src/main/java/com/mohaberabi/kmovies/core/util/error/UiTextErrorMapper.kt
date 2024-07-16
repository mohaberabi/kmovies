package com.mohaberabi.kmovies.core.util.error

import com.mohaberabi.kmovies.R
import com.mohaberabi.kmovies.core.util.UiText


fun AppError.asUiText(): UiText = when (this) {
    is LocalAppError -> toUiText()
    is RemoteAppError -> toUiText()
    is CommonAppError -> toUiText()
    else -> UiText.StringResource(R.string.unknown_error)
}

private fun CommonAppError.toUiText(): UiText {
    val id = when (this) {
        CommonAppError.IO_FAILURE -> R.string.unknown_error
        CommonAppError.UNKNOWN -> R.string.unknown_error
    }
    return UiText.StringResource(id)
}

private fun RemoteAppError.toUiText(): UiText {
    val id = when (this) {
        RemoteAppError.UNKNOWN -> R.string.unknown_error
        RemoteAppError.SOCKET_TIME_OUT -> R.string.unknown_error
        RemoteAppError.UNKNOWN_HOST -> R.string.unknown_error
        RemoteAppError.CONNECTION_ERROR -> R.string.unknown_error
        RemoteAppError.SSL_ERROR -> R.string.unknown_error
        RemoteAppError.SERIALIZATION_EXCEPTION -> R.string.unknown_error
        RemoteAppError.BAD_REQUEST -> R.string.unknown_error
        RemoteAppError.UNAUTHORIZED -> R.string.unknown_error
        RemoteAppError.FORBIDDEN -> R.string.unknown_error
        RemoteAppError.NOT_FOUND -> R.string.unknown_error
        RemoteAppError.METHOD_NOT_ALLOWED -> R.string.unknown_error
        RemoteAppError.CONFLICT -> R.string.unknown_error
        RemoteAppError.UNSUPPORTED_MEDIA -> R.string.unknown_error
        RemoteAppError.INTERNAL_SERVER_ERROR -> R.string.unknown_error
        RemoteAppError.BAD_GATEWAY -> R.string.unknown_error
        RemoteAppError.TIMEOUT -> R.string.unknown_error
        RemoteAppError.NOT_IMPLEMENTED -> R.string.unknown_error
        RemoteAppError.SERVICE_UNAVAILABLE -> R.string.unknown_error
        RemoteAppError.GATEWAY_TIMEOUT -> R.string.unknown_error
    }
    return UiText.StringResource(id)
}

private fun LocalAppError.toUiText(): UiText {
    val id = when (this) {
        LocalAppError.CONSTRAINS_FAILURE -> R.string.unknown_error
        LocalAppError.DISK_FULL -> R.string.unknown_error
        LocalAppError.INVALID_QUERY -> R.string.unknown_error
        LocalAppError.STALE_DATA -> R.string.unknown_error
    }
    return UiText.StringResource(id)
}