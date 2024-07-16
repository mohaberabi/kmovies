package example.com

import example.com.core.di.appModule
import example.com.core.security.domain.model.TokenConfig
import example.com.core.security.domain.service.TokenService
import example.com.features.auth.di.authModule
import example.com.features.auth.domain.repository.AuthRepository
import example.com.features.user.di.userModule
import example.com.features.user.domain.repository.UserRepository
import example.com.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    install(Koin) {
        slf4jLogger()
        modules(
            appModule,
            userModule,
            authModule
        )
    }
    val authRepository by inject<AuthRepository>()
    val userRepository by inject<UserRepository>()
    val tokenGenerator by inject<TokenService>()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("issuer").getString(),
        audience = environment.config.property("audience").getString(),
        expirationDate = 30L * 1000L * 60 * 60L * 24L,
        // do not do this in a real world app
        secret = "mohab"
    )
    configureSerialization()
    configureSecurity(
        tokenConfig = tokenConfig,
    )

    configureRouting(
        authRepository = authRepository,
        tokenConfig = tokenConfig,
        userRepository = userRepository,
        tokenGenerator = tokenGenerator,
    )
}
