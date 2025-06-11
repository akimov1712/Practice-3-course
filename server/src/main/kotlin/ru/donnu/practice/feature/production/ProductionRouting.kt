package ru.donnu.practice.feature.production

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureProductionRouting(){
    routing {
        get("/production") {
            val controller = ProductionController(this.call)
            controller.getAllProduction()
        }
    }
}