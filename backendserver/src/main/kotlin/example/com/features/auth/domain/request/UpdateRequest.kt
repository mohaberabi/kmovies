package example.com.features.auth.domain.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateRequest(
    val uid: String,
    val name: String,
    val lastname: String,
)