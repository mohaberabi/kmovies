package example.com.core.security.domain.model


data class TokenConfig(
    val issuer: String,
    val audience: String,
    val expirationDate: Long,
    val secret: String
)