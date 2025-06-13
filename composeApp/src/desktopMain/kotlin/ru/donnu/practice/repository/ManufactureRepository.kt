package ru.donnu.practice.repository

import entity.ManufactureEntity
import io.ktor.client.call.*
import io.ktor.http.*
import ru.donnu.practice.network.ApiFactory
import ru.donnu.practice.network.manufacture.ManufactureApi
import ru.donnu.practice.network.manufacture.ManufactureDTO

class ManufactureRepository {

    private val api = ManufactureApi(ApiFactory.client)

    suspend fun deleteManufacture(countryId: Int) = api.deleteManufacture(countryId).status.isSuccess()
    suspend fun addManufactures(manufactures: List<ManufactureDTO>) = api.addManufactures(manufactures).body<List<ManufactureEntity>>()

}