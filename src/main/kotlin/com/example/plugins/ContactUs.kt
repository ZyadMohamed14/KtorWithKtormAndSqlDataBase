package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.contactsUs() {
    routing {
        get("/contactsus") {
            call.respondText {
                "contactsus"
            }
        }
        get("/aboutus") {
            call.respondText {
                "aboutus"
            }
        }
    }
}

