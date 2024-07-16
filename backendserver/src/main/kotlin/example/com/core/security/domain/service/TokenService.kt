package example.com.core.security.domain.service

import example.com.core.security.domain.model.TokenClaim
import example.com.core.security.domain.model.TokenConfig

interface TokenService {


    fun generateToken(
        config: TokenConfig,
        vararg claims: TokenClaim,
    ): String

}