package example.com.core.security.data.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import example.com.core.security.domain.model.TokenClaim
import example.com.core.security.domain.model.TokenConfig
import example.com.core.security.domain.service.TokenService
import java.util.Date

class JWTTokenService : TokenService {
    override fun generateToken(
        config: TokenConfig,
        vararg claims: TokenClaim,
    ): String {
        val token = JWT.create()
            .withIssuer(config.issuer)
            .withAudience(config.audience)
            .withExpiresAt(Date(System.currentTimeMillis() + config.expirationDate))
        claims.forEach { claim ->
            token.withClaim(claim.name, claim.value)
        }
        return token.sign(Algorithm.HMAC256(config.secret))
    }

}