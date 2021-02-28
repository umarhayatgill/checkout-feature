package com.checkout

import com.checkout.CheckoutService.checkoutWatches
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.features.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        post("/checkout") {
            try {
                val request = call.receive<ArrayList<String>>()
                call.respond(mapOf("price" to checkoutWatches(request)))
            } catch (exception: Exception) {
                log.error("Exception occurred", exception)
            }
        }
    }
}

