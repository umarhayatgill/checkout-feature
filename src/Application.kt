package com.checkout

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

private val checkoutService = CheckoutService(DiscountService(), PricingService())

fun Application.module() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        post("/checkout") {
            try {
                val request = call.receive<ArrayList<String>>()
                call.respond(mapOf("price" to checkoutService.checkoutWatches(request)))
            } catch (exception: Exception) {
                log.error("Exception occurred", exception)
            }
        }
    }
}

