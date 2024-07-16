package example.com.features.user.data.repository

import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import example.com.core.data.entity.UserEntity
import example.com.core.data.entity.toUserModel
import example.com.core.util.DispatcherProvider
import example.com.core.util.const.DbColl
import example.com.core.util.error.CommonErrors
import example.com.core.util.extensions.toDatabaseError
import example.com.features.auth.domain.request.SignupRequest
import example.com.features.user.domain.model.UserModel
import example.com.features.user.domain.repository.CreateUserFailure
import example.com.features.user.domain.repository.GetUSerFailure
import example.com.features.user.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext


class MongoUserRepository(
    private val dispatchers: DispatcherProvider,
    private val mongo: com.mongodb.kotlin.client.coroutine.MongoDatabase,
) : UserRepository {
    override suspend fun createUser(
        request: SignupRequest,
        uid: String,
    ): UserModel {
        return withContext(dispatchers.io) {
            try {
                val entity = UserEntity(
                    name = request.name,
                    lastname = request.lastname,
                    email = request.email,
                    uid = uid,
                    mediaList = emptyList(),
                )
                val result = mongo.getCollection<UserEntity>(DbColl.USER_COLL)
                    .insertOne(
                        entity
                    )
                if (result.insertedId == null) {
                    throw CreateUserFailure(error = CommonErrors.Unknown)
                }
                entity.toUserModel()
            } catch (e: MongoException) {
                throw GetUSerFailure(e.toDatabaseError())
            }
        }
    }

    override suspend fun getUser(uid: String): UserModel {
        return withContext(dispatchers.io) {
            try {
                val result = mongo.getCollection<UserEntity>(DbColl.USER_COLL)
                    .withDocumentClass<UserEntity>()
                    .find(Filters.eq("_id", uid))
                    .firstOrNull() ?: throw GetUSerFailure(CommonErrors.NOT_EXIST)
                result.toUserModel()
            } catch (e: MongoException) {
                throw GetUSerFailure(e.toDatabaseError())
            }
        }
    }
}