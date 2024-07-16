package example.com.core.util.error

import io.ktor.http.*


fun AppErrorType.toHttpStatusCode(): HttpStatusCode {
    return when (this) {
        is AuthErrors -> this.toStatusCode()
        is DatabaseErrors -> this.toStatusCode()
        else -> HttpStatusCode.InternalServerError
    }
}

private fun AuthErrors.toStatusCode(): HttpStatusCode {
    return when (this) {
        AuthErrors.NOT_EXIST -> HttpStatusCode.NotFound
        AuthErrors.USER_ALREADY_EXISTS -> HttpStatusCode.Conflict
        AuthErrors.WRONG_PASSWORD -> HttpStatusCode.Unauthorized
        AuthErrors.WRONG_CREDENTIALS -> HttpStatusCode.Unauthorized
        AuthErrors.INVALID_EMAIL -> HttpStatusCode.BadRequest
        AuthErrors.WEAK_PASSWORD -> HttpStatusCode.BadRequest
    }

}

private fun DatabaseErrors.toStatusCode(): HttpStatusCode {
    return when (this) {
        DatabaseErrors.CONNECTION_TIMEOUT -> HttpStatusCode.RequestTimeout
        DatabaseErrors.AUTHENTICATION_FAILED -> HttpStatusCode.Unauthorized
        DatabaseErrors.DUPLICATE_KEY -> HttpStatusCode.Conflict
        DatabaseErrors.VALIDATION_ERROR -> HttpStatusCode.BadRequest
        DatabaseErrors.NETWORK_ERROR -> HttpStatusCode.ServiceUnavailable
        DatabaseErrors.WRITE_CONCERN_ERROR -> HttpStatusCode.InternalServerError
        DatabaseErrors.UNKNOWN_ERROR -> HttpStatusCode.InternalServerError
        DatabaseErrors.DOCUMENT_NOT_FOUND -> HttpStatusCode.NotFound
        DatabaseErrors.UNAUTHORIZED_OPERATION -> HttpStatusCode.Forbidden
        DatabaseErrors.TRANSACTION_ABORTED -> HttpStatusCode.InternalServerError
    }
}