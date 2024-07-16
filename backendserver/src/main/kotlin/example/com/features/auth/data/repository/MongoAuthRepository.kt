package example.com.features.auth.data.repository

import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import example.com.core.data.entity.AuthEntity
import example.com.core.security.domain.service.HashingService
import example.com.core.util.DispatcherProvider
import example.com.core.util.InputValidators
import example.com.core.util.const.DbColl
import example.com.core.util.error.AuthErrors
import example.com.core.util.error.CommonErrors
import example.com.features.auth.domain.repository.AuthRepository
import example.com.features.auth.domain.repository.SignInFailure
import example.com.features.auth.domain.repository.SignUpFailure
import example.com.features.auth.domain.request.SignInRequest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext


class MongoAuthRepository(
    private val mongo: MongoDatabase,
    private val disptachers: DispatcherProvider,
    private val hasherService: HashingService,
    private val validator: InputValidators
) : AuthRepository {
    override suspend fun signIn(signInRequest: SignInRequest): String {

        return withContext(disptachers.io) {
            try {
                val result = mongo.getCollection<AuthEntity>(DbColl.AUTH_COLL)
                    .withDocumentClass<AuthEntity>()
                    .find(Filters.eq("_id", signInRequest.email))
                    .firstOrNull() ?: throw SignInFailure(AuthErrors.NOT_EXIST)
                val passwordMatches =
                    hasherService.verify(
                        plain = signInRequest.password,
                        storedHash = result.hashPassword,
                        storedSalt = result.salt,
                    )
                if (!passwordMatches) {
                    throw SignInFailure(AuthErrors.WRONG_PASSWORD)
                }
                result.uid.toHexString()
            } catch (e: MongoException) {
                System.err.println(e.toString())
                throw SignInFailure(
                    error = CommonErrors.Unknown,
                    message = e.message
                )
            }


        }
    }

    override suspend fun signUp(
        email: String,
        password: String,
    ): String? {
        return withContext(disptachers.io) {
            try {
                val validEmail = validator.validateEmail(email)
                if (!validEmail) {
                    throw SignUpFailure(
                        error = AuthErrors.INVALID_EMAIL,
                    )
                }
                val validPassword = validator.valdiatePassword(password)
                if (!validPassword) {
                    throw SignUpFailure(
                        error = AuthErrors.WRONG_PASSWORD,
                    )
                }
                val userOrNull = mongo.getCollection<AuthEntity>(DbColl.AUTH_COLL)
                    .withDocumentClass<AuthEntity>()
                    .find(Filters.eq("_id", email)).firstOrNull()
                if (userOrNull != null) {
                    throw SignUpFailure(
                        error = AuthErrors.USER_ALREADY_EXISTS,
                    )
                }
                val hashed = hasherService.generateHash(password)
                val auth = AuthEntity(
                    email = email,
                    hashPassword = hashed.hash,
                    salt = hashed.salt
                )
                mongo.getCollection<AuthEntity>(DbColl.AUTH_COLL).insertOne(auth)
                auth.uid.toHexString()
            } catch (e: MongoException) {
                System.err.println(e.toString())
                throw SignUpFailure(
                    error = CommonErrors.Unknown,
                    message = e.message
                )
            }
        }
    }


}