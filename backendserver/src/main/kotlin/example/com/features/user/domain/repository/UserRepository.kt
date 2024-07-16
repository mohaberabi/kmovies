package example.com.features.user.domain.repository

import example.com.core.util.error.AppErrorType
import example.com.core.util.error.AppException
import example.com.features.auth.domain.request.SignupRequest
import example.com.features.user.domain.model.UserModel


interface UserRepository {
    suspend fun createUser(request: SignupRequest, uid: String): UserModel
    suspend fun getUser(uid: String): UserModel
}


data class CreateUserFailure(
    override val error: AppErrorType,
    override val message: String? = null
) : AppException(error = error, message = message)

data class GetUSerFailure(
    override val error: AppErrorType,
    override val message: String? = null
) : AppException(error = error, message = message)