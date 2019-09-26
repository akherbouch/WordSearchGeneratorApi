package me.akherbouch.wsg

import com.ryanharter.ktor.moshi.moshi
import io.ktor.application.*
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.routing
import me.akherbouch.wsg.api.main.fromWords
import me.akherbouch.wsg.api.random.random


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(DefaultHeaders)

    install(StatusPages) {
        exception<Throwable> { e ->
            call.respondText(e.localizedMessage,
                ContentType.Text.Plain, HttpStatusCode.InternalServerError)
        }
    }

    install(ContentNegotiation) {
        moshi()
    }

    install(CORS) {
        anyHost()
    }

    routing {
        random()
        fromWords()
        get("/") {
            call.respondText("Hi")
        }
    }

}

const val API_VERSION = "/api/v1"

