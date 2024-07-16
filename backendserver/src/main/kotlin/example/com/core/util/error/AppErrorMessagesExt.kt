package example.com.core.util.error

fun AppErrorType.toMessage(): String {
    return when (this) {
        is AuthErrors -> this.toErrorMessage()
        is DatabaseErrors -> this.toErrorMessage()
        else -> ErrorMessages.UNKNOWN_ERROR
    }
}

private fun AuthErrors.toErrorMessage(): String {
    return when (this) {
        AuthErrors.NOT_EXIST -> ErrorMessages.INVALID_EMAIL
        AuthErrors.USER_ALREADY_EXISTS -> ErrorMessages.USER_ALREADY_EXISTS
        AuthErrors.WRONG_PASSWORD -> ErrorMessages.WRONG_PASSWORD
        AuthErrors.WRONG_CREDENTIALS -> ErrorMessages.WRONG_CREDENTIALS
        AuthErrors.INVALID_EMAIL -> ErrorMessages.INVALID_EMAIL
        AuthErrors.WEAK_PASSWORD -> ErrorMessages.WEAK_PASSWORD
    }
}


private fun DatabaseErrors.toErrorMessage(): String {
    return when (this) {
        DatabaseErrors.CONNECTION_TIMEOUT -> ErrorMessages.CONNECTION_TIMEOUT
        DatabaseErrors.AUTHENTICATION_FAILED -> ErrorMessages.AUTHENTICATION_FAILED
        DatabaseErrors.DUPLICATE_KEY -> ErrorMessages.DUPLICATE_KEY
        DatabaseErrors.VALIDATION_ERROR -> ErrorMessages.VALIDATION_ERROR
        DatabaseErrors.NETWORK_ERROR -> ErrorMessages.NETWORK_ERROR
        DatabaseErrors.WRITE_CONCERN_ERROR -> ErrorMessages.WRITE_CONCERN_ERROR
        DatabaseErrors.UNKNOWN_ERROR -> ErrorMessages.UNKNOWN_ERROR
        DatabaseErrors.DOCUMENT_NOT_FOUND -> ErrorMessages.DOCUMENT_NOT_FOUND
        DatabaseErrors.UNAUTHORIZED_OPERATION -> ErrorMessages.UNAUTHORIZED_OPERATION
        DatabaseErrors.TRANSACTION_ABORTED -> ErrorMessages.TRANSACTION_ABORTED
    }
}