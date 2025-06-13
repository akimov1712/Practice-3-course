package ru.donnu.practice.network.production

import io.ktor.client.*
import io.ktor.client.request.*
import ru.donnu.practice.network.manufacture.ManufactureDTO

class ProductionApi(private val client: HttpClient) {

    suspend fun getAllProduction() = client.get("/production")

}