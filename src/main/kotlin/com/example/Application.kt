package com.example

import com.example.plugins.*
import com.example.routing.noteRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import org.ktorm.database.Database
import org.ktorm.dsl.*


fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1"){
        install(ContentNegotiation) {
            json()
        }
        //contactsUs()
        configureRouting()
       // noteRouting()



    }.start(wait = true)
}

/*
fun Application.module() {

}

 */
