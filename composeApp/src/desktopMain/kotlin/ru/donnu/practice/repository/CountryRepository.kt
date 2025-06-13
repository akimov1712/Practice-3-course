package ru.donnu.practice.repository

import entity.CountryEntity
import io.ktor.client.call.*
import ru.donnu.practice.network.ApiFactory
import ru.donnu.practice.network.country.CountryApi

class CountryRepository() {

    private val api = CountryApi(ApiFactory.client)

    suspend fun getAllCountries() = api.getCountries().body<List<CountryEntity>>()

}