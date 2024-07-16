package example.com.features.auth.domain.response

import kotlinx.serialization.Serializable


@Serializable
data class AuthResponse(
    val uid: String,
    val name: String,
    val lastname: String,
    val email: String,
    val token: String,
)