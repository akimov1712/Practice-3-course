package ru.donnu.practice.network.country

import io.ktor.client.*
import io.ktor.client.request.*

class CountryApi(private val client: HttpClient) {

    suspend fun getCountries() = client.get("/country")

}