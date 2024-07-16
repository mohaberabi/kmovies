package example.com.core.security.data.service

import example.com.core.security.domain.service.StringHasher
import example.com.core.security.util.SecuritySecrets
import java.security.MessageDigest
import java.util.*

class SHA256StringHahser : StringHasher {


    override operator fun invoke(plain: String, salt: String): String {
        val digest = MessageDigest.getInstance(SecuritySecrets.SHA_256)
        val saltBytes = Base64.getDecoder().decode(salt)
        digest.update(saltBytes)
        val hashBytes = digest.digest(plain.toByteArray(Charsets.UTF_8))
        return Base64.getEncoder().encodeToString(hashBytes)
    }
}