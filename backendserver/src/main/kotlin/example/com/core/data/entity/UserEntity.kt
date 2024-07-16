package example.com.core.data.entity

import example.com.features.user.domain.model.UserModel
import org.bson.codecs.pojo.annotations.BsonId


data class UserEntity(
    @BsonId
    val uid: String,
    val name: String,
    val lastname: String,
    val email: String,
    val mediaList: List<String>,
)


fun UserEntity.toUserModel(): UserModel = UserModel(
    uid = uid,
    name = name,
    lastname = lastname,
    email = email,
    mediaList = mediaList
)

fun UserModel.toUserEntity(): UserEntity = UserEntity(
    uid = uid,
    name = name,
    lastname = lastname,
    email = email,
    mediaList = mediaList
)