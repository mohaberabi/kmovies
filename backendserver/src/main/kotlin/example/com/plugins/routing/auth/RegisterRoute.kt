package example.com.plugins.routing.auth

import example.com.core.security.domain.model.TokenClaim
import example.com.core.security.domain.model.TokenConfig
import example.com.core.security.domain.service.TokenService
import example.com.core.util.error.CommonErrors
import example.com.core.util.error.ErrorMessages
import example.com.core.util.extensions.respondError
import example.com.features.auth.domain.repository.AuthRepository
import example.com.features.auth.domain.repository.SignUpFailure
import example.com.features.auth.domain.request.SignupRequest
import example.com.features.auth.domain.response.AuthResponse
import example.com.features.user.domain.repository.CreateUserFailure
import example.com.features.user.domain.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.register(
    tokenGenerator: TokenService,
    authRepository: AuthRepository,
    userRepository: UserRepository,
    tokenConfig: TokenConfig,
) {
    post {

        val request = call.receiveNullable<SignupRequest>()

        if (request == null) {
            call.respondError(
                statusCode = HttpStatusCode.BadRequest,
                message = ErrorMessages.BLANK_FIELDS,
                errorId = ErrorMessages.BLANK_FIELDS
            )
            return@post
        }

        try {
            val uid = authRepository.signUp(
                email = request.email,
                password = request.password
            )
            if (uid == null) {
                call.respondError(
                    statusCode = HttpStatusCode.InternalServerError,
                    message = ErrorMessages.INTERNAL_ERROR,
                    errorId = CommonErrors.INTENRAL_ERROR.name
                )
                return@post
            }

            val user = userRepository.createUser(
                uid = uid,
                request = request,
            )

            val authResponse = AuthResponse(
                name = user.name,
                uid = uid,
                lastname = user.lastname,
                email = user.email,
                token = tokenGenerator.generateToken(
                    config = tokenConfig,
                    claims = arrayOf(TokenClaim(name = "email", value = user.email))
                )
            )
            call.respond(HttpStatusCode.OK, authResponse)
            return@post
        } catch (e: SignUpFailure) {
            call.respondError(e.error)
            return@post
        } catch (e: CreateUserFailure) {
            call.respondError(e.error)
            return@post
        }
    }
}