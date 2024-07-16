package example.com.core.security.data.service

import example.com.core.security.domain.model.SecureHasher
import example.com.core.security.domain.service.HashingService
import example.com.core.security.domain.service.SaltGenerator
import example.com.core.security.domain.service.StringHasher

class SHA256HashService(
    private val saltGenerator: SaltGenerator,
    private val stringHasher: StringHasher,
) : HashingService {


    override fun generateHash(
        value: String,
        saltSize: Int,
    ): SecureHasher {
        val salt = saltGenerator(saltSize)
        val hashed = stringHasher(
            plain = value,
            salt = salt,
        )
        return SecureHasher(
            hash = hashed,
            salt = salt
        )
    }


    override fun verify(
        storedSalt: String,
        plain: String,
        storedHash: String,
    ): Boolean {
        val newHash = stringHasher(
            plain = plain,
            salt = storedSalt,
        )
        return storedHash == newHash
    }
}