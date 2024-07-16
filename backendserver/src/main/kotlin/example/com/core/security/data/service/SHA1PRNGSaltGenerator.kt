package example.com.core.security.data.service

import example.com.core.security.domain.service.SaltGenerator
import example.com.core.security.util.SecuritySecrets
import java.security.SecureRandom
import java.util.*

class SHA1PRNGSaltGenerator : SaltGenerator {
    override operator fun invoke(size: Int): String {
        val random = SecureRandom.getInstance(SecuritySecrets.SALT_ALGO)
        val salt = ByteArray(size)
        random.nextBytes(salt)
        return Base64.getEncoder().encodeToString(salt)
    }
}