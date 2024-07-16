package example.com.core.util.extensions

import example.com.core.util.error.AppErrorType
import example.com.core.util.error.ErrorModel
import example.com.core.util.error.toErrorModel
import example.com.core.util.error.toHttpStatusCode
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*


suspend fun ApplicationCall.respondError(
    error: AppErrorType
) = respond(
    error.toHttpStatusCode(),
    error.toErrorModel,
)


suspend fun ApplicationCall.respondError(
    statusCode: HttpStatusCode,
    message: String,
    errorId: String? = null
) {
    val errorResponse = ErrorModel(
        message = message,
        statusCode = statusCode.value,
        errorCode = errorId ?: "UnknownError"
    )
    respond(statusCode, errorResponse)
}
