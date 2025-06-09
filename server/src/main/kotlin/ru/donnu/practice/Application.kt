 package ru.donnu.practice

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.donnu.practice.plugins.configureDatabases
import ru.donnu.practice.plugins.configureRouting
import ru.donnu.practice.plugins.configureSerialization
import ru.donnu.practice.utills.Env

 fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDatabases()
    configureRouting()
    configureSerialization()
}