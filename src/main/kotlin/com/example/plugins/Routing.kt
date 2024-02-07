package com.example.plugins

import com.example.DataBaseConnection
import com.example.Note
import com.example.NoteEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.ktorm.dsl.from
import org.ktorm.dsl.map
import org.ktorm.dsl.select
import java.io.File

fun Application.configureRouting() {
    val db = DataBaseConnection.database
    routing {

        get("/notes") {
            val notes = db.from(NoteEntity).select()
                .map {
                    val id = it[NoteEntity.id]
                    val note = it[NoteEntity.note]

                    Note(id ?: -1, note ?: "")
                }
            call.respond(notes)
        }
        get("/") {
            call.respondText("Hello World!")
            println("Headers${call.request.uri}")
            println("Headers${call.request.headers.names()}")

        }

        get("/fileDownload") {
            val file = File("files/phone.jpg")
            call.response.header(
                HttpHeaders.ContentDisposition, ContentDisposition.Attachment.withParameter(
                    ContentDisposition.Parameters.FileName, "downloadableImage.png"
                ).toString()
            )
            call.respondFile(file)
        }
        get("/openfile") {
            val file = File("files/personaccountjpg.jpg")
            call.response.header(
                HttpHeaders.ContentDisposition, ContentDisposition.Inline.withParameter(
                    ContentDisposition.Parameters.FileName, "downloadableImage.png"
                ).toString()
            )
            call.respondFile(file)
        }
        post("/login") {
            val userInfo = call.receive<UserInfo>()
            println(userInfo)

            call.respondText { "every thing is work " }
        }
    }
}

@Serializable
data class UserInfo(val email: String, val password: String)
