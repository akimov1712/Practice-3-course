package ru.donnu.practice.feature.manufacture

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureManufactureRouting(){
    routing {
        post("/manufacture") {
            val controller = ManufactureController(call)
            controller.insertManufacture()
        }
        delete("/manufacture/{countryId}") {
            val controller = ManufactureController(call)
            controller.deleteRecipe()
        }
    }
}