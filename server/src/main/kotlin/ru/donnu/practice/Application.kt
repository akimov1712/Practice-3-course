 package ru.donnu.practice

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.donnu.practice.plugins.configureDatabases
import ru.donnu.practice.plugins.configureRouting
import ru.donnu.practice.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDatabases()
    configureRouting()
    configureSerialization()

}