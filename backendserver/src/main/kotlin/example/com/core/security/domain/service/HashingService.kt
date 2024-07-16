package example.com.core.security.domain.service

import example.com.core.security.domain.model.SecureHasher
import example.com.core.security.util.SecuritySecrets

interface HashingService {


    fun generateHash(
        value: String,
        saltSize: Int = SecuritySecrets.SALT_SIZE,
    ): SecureHasher


    fun verify(
        storedSalt: String,
        plain: String,
        storedHash: String,
    ): Boolean
}