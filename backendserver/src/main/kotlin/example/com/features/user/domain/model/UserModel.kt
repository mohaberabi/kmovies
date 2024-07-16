package example.com.features.user.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val name: String,
    val lastname: String,
    val email: String,
    val uid: String,
    val mediaList: List<String> = emptyList(),
)