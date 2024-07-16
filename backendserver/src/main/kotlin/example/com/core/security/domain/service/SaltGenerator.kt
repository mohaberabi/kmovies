package example.com.core.security.domain.service

interface SaltGenerator {


    operator fun invoke(size: Int): String
}

