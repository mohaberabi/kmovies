package example.com.core.security.domain.model

data class SecureHasher(
    val hash: String,
    val salt: String,
)
