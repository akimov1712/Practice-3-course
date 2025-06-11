package ru.donnu.practice.feature.manufacture

import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.donnu.practice.model.manufacture.ManufactureTable
import ru.donnu.practice.model.manufacture.ManufactureType

class ManufactureController(private val call: RoutingCall) {

    suspend fun insertManufacture(){
        val receiveManufacture = call.receive<ManufactureReceive>()
        val type = ManufactureType.valueOf(receiveManufacture.type)
        val result = ManufactureTable.insertManufacture(receiveManufacture.countryId, type, receiveManufacture.value)
        return call.respond(result)
//    } catch (e: Exception){
//        call.respond(HttpStatusCode.BadRequest)
//    }

}
}