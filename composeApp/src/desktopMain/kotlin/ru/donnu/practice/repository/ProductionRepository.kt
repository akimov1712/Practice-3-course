package ru.donnu.practice.repository

import entity.CountryEntity
import entity.ProductionEntity
import io.ktor.client.call.*
import ru.donnu.practice.network.ApiFactory
import ru.donnu.practice.network.country.CountryApi
import ru.donnu.practice.network.production.ProductionApi

class ProductionRepository() {

    private val api = ProductionApi(ApiFactory.client)

    suspend fun getAllProduction() = api.getAllProduction().body<List<ProductionEntity>>()

}