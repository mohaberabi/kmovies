package example.com.features.auth.domain.repository

import example.com.core.util.error.AppErrorType
import example.com.core.util.error.AppException
import example.com.features.auth.domain.request.SignInRequest


interface AuthRepository {
    suspend fun signIn(
        signInRequest: SignInRequest
    ): String

    suspend fun signUp(
        email: String,
        password: String,
    ): String?
}

data class SignUpFailure(
    override val error: AppErrorType,
    override val message: String? = null
) : AppException(error = error, message = message)

data class SignInFailure(
    override val error: AppErrorType,
    override val message: String? = null
) : AppException(error = error, message = message)