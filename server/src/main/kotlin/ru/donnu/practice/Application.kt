 package ru.donnu.practice

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import ru.donnu.practice.model.country.CountryEntity
import ru.donnu.practice.model.country.CountryTable
import ru.donnu.practice.model.region.RegionEnum
import ru.donnu.practice.model.region.RegionTable
import ru.donnu.practice.plugins.configureDatabases
import ru.donnu.practice.plugins.configureRouting
import ru.donnu.practice.plugins.configureSerialization
import ru.donnu.practice.utills.Env
import java.io.File

 fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDatabases()
    configureRouting()
    configureSerialization()
}