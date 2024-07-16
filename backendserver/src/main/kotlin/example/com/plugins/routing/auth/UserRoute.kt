package example.com.plugins.routing.auth

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.user() {
    authenticate {
        get("/user") {
            val principal = call.principal<JWTPrincipal>()
            val email = principal?.payload?.claims?.get("email") ?: ""
            call.respond(HttpStatusCode.OK, "Granted ${email}")
        }
    }

}