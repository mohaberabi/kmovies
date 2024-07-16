package example.com.features.auth.domain.response

import kotlinx.serialization.Serializable


@Serializable
data class UserResponse(
    val name: String,
    val lastname: String,
    val email: String,
    val uid: String
)
