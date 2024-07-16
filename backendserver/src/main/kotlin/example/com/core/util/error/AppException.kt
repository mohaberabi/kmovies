package example.com.core.util.error

import kotlinx.serialization.Serializable


interface AppErrorType {
    val name: String
}


abstract class AppException(
    open val error: AppErrorType,
    override val message: String? = null,
) : Throwable(message = message)


enum class AuthErrors : AppErrorType {
    NOT_EXIST,
    USER_ALREADY_EXISTS,
    WRONG_PASSWORD,
    WRONG_CREDENTIALS,
    INVALID_EMAIL,
    WEAK_PASSWORD,
}

enum class CommonErrors : AppErrorType {
    Unknown,
    NOT_EXIST,
    INTENRAL_ERROR,

}

enum class DatabaseErrors : AppErrorType {
    CONNECTION_TIMEOUT,
    AUTHENTICATION_FAILED,
    DUPLICATE_KEY,
    VALIDATION_ERROR,
    NETWORK_ERROR,
    WRITE_CONCERN_ERROR,
    UNKNOWN_ERROR,
    DOCUMENT_NOT_FOUND,
    UNAUTHORIZED_OPERATION,
    TRANSACTION_ABORTED
}

@Serializable
data class ErrorModel(
    val errorCode: String,
    val message: String? = null,
    val statusCode: Int
)


val AppErrorType.toErrorModel: ErrorModel
    get() = ErrorModel(
        errorCode = this.name,
        statusCode = this.toHttpStatusCode().value,
        message = this.toMessage()
    )
