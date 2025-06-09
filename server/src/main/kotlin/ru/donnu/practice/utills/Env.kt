package ru.donnu.practice.utills

import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.plugins.*

object Env {

    private val env = dotenv()

    operator fun get(key: String): String = env[key] ?: throw NotFoundException("Value with key:\"$key\" not found")

}