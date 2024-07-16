package example.com.plugins.routing


import example.com.core.security.domain.model.TokenConfig
import example.com.core.security.domain.service.TokenService
import example.com.core.util.error.CommonErrors
import example.com.core.util.error.ErrorMessages
import example.com.core.util.extensions.respondError
import example.com.features.auth.domain.repository.AuthRepository
import example.com.features.auth.domain.repository.SignInFailure
import example.com.features.auth.domain.repository.SignUpFailure
import example.com.features.auth.domain.request.SignInRequest
import example.com.features.auth.domain.request.SignupRequest
import example.com.features.auth.domain.response.AuthResponse
import example.com.features.user.domain.repository.CreateUserFailure
import example.com.features.user.domain.repository.GetUSerFailure
import example.com.features.user.domain.repository.UserRepository
import example.com.plugins.routing.auth.login
import example.com.plugins.routing.auth.register
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


private object AuthRoutesConst {
    const val LOG_IN = "/login"
    const val REGISTER = "/register"
    const val USER = "/user"

}

fun Application.authRoutes(
    tokenGenerator: TokenService,
    authRepository: AuthRepository,
    userRepository: UserRepository,
    tokenConfig: TokenConfig,
) {
    routing {

        route(AuthRoutesConst.LOG_IN) {
            login(
                tokenGenerator = tokenGenerator,
                authRepository = authRepository,
                userRepository = userRepository,
                tokenConfig = tokenConfig,
            )
        }
        route(AuthRoutesConst.REGISTER) {
            register(
                tokenGenerator = tokenGenerator,
                authRepository = authRepository,
                userRepository = userRepository,
                tokenConfig = tokenConfig,
            )
        }
    }
}