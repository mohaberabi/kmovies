package example.com.core.data.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId


data class AuthEntity(
    @BsonId
    val email: String,
    val hashPassword: String,
    val salt: String,
    val uid: ObjectId = ObjectId()
)