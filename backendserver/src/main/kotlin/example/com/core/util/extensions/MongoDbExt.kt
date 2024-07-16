package example.com.core.util.extensions

import com.mongodb.*
import example.com.core.util.error.DatabaseErrors

fun MongoException.toDatabaseError(): DatabaseErrors {
    return when (this) {
        is MongoTimeoutException -> DatabaseErrors.CONNECTION_TIMEOUT
        is MongoSecurityException -> DatabaseErrors.AUTHENTICATION_FAILED
        is DuplicateKeyException -> DatabaseErrors.DUPLICATE_KEY
        is MongoWriteException -> {
            if (this.error.category == ErrorCategory.DUPLICATE_KEY) {
                DatabaseErrors.DUPLICATE_KEY
            } else {
                DatabaseErrors.WRITE_CONCERN_ERROR
            }
        }

        is MongoWriteConcernException -> DatabaseErrors.WRITE_CONCERN_ERROR
        is MongoCommandException -> {
            when (this.code) {
                11000, 11001 -> DatabaseErrors.DUPLICATE_KEY
                121 -> DatabaseErrors.VALIDATION_ERROR
                else -> DatabaseErrors.UNKNOWN_ERROR
            }
        }

        else -> DatabaseErrors.UNKNOWN_ERROR
    }
}
