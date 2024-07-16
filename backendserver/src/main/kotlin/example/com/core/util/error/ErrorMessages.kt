package example.com.core.util.error

object ErrorMessages {
    const val INVALID_EMAIL = "Invalid email. Please provide a valid email address."
    const val USER_ALREADY_EXISTS = "User with this email already exists."
    const val WRONG_PASSWORD = "Incorrect password provided."
    const val WRONG_CREDENTIALS = "Incorrect credentials provided."
    const val WEAK_PASSWORD = "Password provided is too weak."
    const val CONNECTION_TIMEOUT = "Connection to the database timed out."
    const val AUTHENTICATION_FAILED = "Authentication with the database failed."
    const val DUPLICATE_KEY = "Duplicate key error occurred."
    const val VALIDATION_ERROR = "Validation error occurred."
    const val NETWORK_ERROR = "Network error occurred while communicating with the database."
    const val WRITE_CONCERN_ERROR = "Error occurred with write concern."
    const val UNKNOWN_ERROR = "An unknown error occurred."
    const val DOCUMENT_NOT_FOUND = "Requested document was not found."
    const val UNAUTHORIZED_OPERATION = "Unauthorized operation attempted."
    const val TRANSACTION_ABORTED = "Transaction aborted."
    const val BLANK_FIELDS = "This fields are all required please provide "
    const val INTERNAL_ERROR = "Internal Server Error , try again later..."
    const val PARSING_ERROR = "Error Parsing requested data , please recheck input fields "

}
