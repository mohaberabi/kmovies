package example.com.features.auth.domain.request

import kotlinx.serialization.Serializable


@Serializable
data class SignupRequest(
    val email: String,
    val password: String,
    val name: String,
    val lastname: String,
)

@Serializable
data class SignInRequest(
    val email: String,
    val password: String,
)