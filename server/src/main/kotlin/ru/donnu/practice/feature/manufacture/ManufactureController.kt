package ru.donnu.practice.feature.manufacture

import entity.ManufactureType
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.donnu.practice.tables.manufacture.ManufactureTable

class ManufactureController(private val call: RoutingCall) {

    suspend fun insertManufacture() = try{
        val receiveManufacture = call.receive<List<ManufactureReceive>>()
        val result = receiveManufacture.map {
            val type = ManufactureType.valueOf(it.type)
            ManufactureTable.insertManufacture(it.countryId, type, it.value)
        }
        call.respond(result)
    } catch(e: Exception){
        e.printStackTrace()
        call.respond(HttpStatusCode.BadRequest)
    }

    suspend fun deleteRecipe() {
        val id = call.parameters["countryId"]?.toIntOrNull() ?: return call.respond(HttpStatusCode.BadRequest)
        ManufactureTable.deleteWithId(id)
        call.respond(HttpStatusCode.OK)
    }

}