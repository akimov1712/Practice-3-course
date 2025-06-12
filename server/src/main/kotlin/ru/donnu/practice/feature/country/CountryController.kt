package ru.donnu.practice.feature.country

import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingCall
import ru.donnu.practice.tables.country.CountryTable

class CountryController(private val call: RoutingCall) {

    suspend fun getCountryList(){
        val countries = CountryTable.selectCountryList()
        call.respond(countries)
    }

}