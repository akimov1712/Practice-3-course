package ru.donnu.practice.feature.country

import io.ktor.server.application.Application
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureCountryRouting(){
    routing {
        get("country"){
            val controller = CountryController(call)
            controller.getCountryList()
        }
    }
}