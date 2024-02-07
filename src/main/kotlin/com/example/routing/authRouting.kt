package com.example.routing

import com.example.DataBaseConnection
import com.example.entities.UserEntity
import com.example.entities.model.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.insert

fun Application.authRouting() {
    val db = DataBaseConnection.userDatabase
    routing {
        post("/register") {
            val user = call.receive<User>()
            if (!user.VaildInputData()) {
                call.respond(HttpStatusCode.BadRequest, "Enter Vaild Data{userName should be in 5 length and password should br at leaste 8}")
                return@post
            } else {
                val username = user.username
                val password = user.encruptPassword()
                db.insert(UserEntity) {
                    set(it.username, username)
                    set(it.password, password)
                }

                call.respondText { "auth done" }
            }


        }

    }
}