package ru.donnu.practice.feature.production

import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.donnu.practice.model.country.CountryTable
import ru.donnu.practice.model.manufacture.ManufactureTable
import ru.donnu.practice.model.production.ProductionEntity

class ProductionController(private val call: RoutingCall) {

    suspend fun getAllProduction(){
        val manufacturers = ManufactureTable.selectAllManufacture().groupBy { it.countryId }
        val productions = manufacturers.map {
            val country = CountryTable.selectWithId(it.key)
            ProductionEntity(
                country = country,
                manufacturers = it.value
            )
        }
        call.respond(productions)
    }

}