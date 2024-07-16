package example.com.core.security.domain.service

interface StringHasher {


    operator fun invoke(plain: String, salt: String): String

}