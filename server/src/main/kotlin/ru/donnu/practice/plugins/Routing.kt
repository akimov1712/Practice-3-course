package ru.donnu.practice.plugins

import io.ktor.server.application.*
import ru.donnu.practice.feature.manufacture.configureManufactureRouting
import ru.donnu.practice.feature.production.configureProductionRouting


fun Application.configureRouting() {
    configureProductionRouting()
    configureManufactureRouting()
}