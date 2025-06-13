package ru.donnu.practice.network.manufacture

import io.ktor.client.*
import io.ktor.client.request.*

class ManufactureApi(private val client: HttpClient) {

    suspend fun deleteManufacture(countryId: Int) = client.delete("/manufacture/$countryId")

    suspend fun addManufactures(manufactures: List<ManufactureDTO>) = client.post("/manufacture")

}