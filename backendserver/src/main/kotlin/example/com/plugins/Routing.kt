package example.com.plugins

import example.com.core.security.domain.model.TokenConfig
import example.com.core.security.domain.service.TokenService
import example.com.features.auth.domain.repository.AuthRepository
import example.com.features.user.domain.repository.UserRepository
import example.com.plugins.routing.authRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    tokenGenerator: TokenService,
    authRepository: AuthRepository,
    userRepository: UserRepository,
    tokenConfig: TokenConfig,
) {
    authRoutes(
        tokenConfig = tokenConfig,
        authRepository = authRepository,
        userRepository = userRepository,
        tokenGenerator = tokenGenerator
    )
}
