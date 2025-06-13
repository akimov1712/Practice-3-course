package ru.donnu.practice.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object ApiFactory {

    val client = HttpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = 30000
            socketTimeoutMillis = 30000
        }
        install(ContentNegotiation) {
            json(
                Json { ignoreUnknownKeys = true }
            )
        }

        defaultRequest {
            contentType(Json.withParameter("charset", "utf-8"))
            url(BASE_URL)
        }
    }

    private const val BASE_URL = "http://127.0.0.1:8080"

}