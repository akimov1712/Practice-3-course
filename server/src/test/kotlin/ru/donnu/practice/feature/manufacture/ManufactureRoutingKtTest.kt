package ru.donnu.practice.feature.manufacture

import entity.ManufactureType
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class ManufactureRoutingKtTest {

    @Test
    fun testPostManufacture() = testApplication {
        application {}
        val client = HttpClient()
        client.post("http://localhost/manufacture"){
            val respond = ManufactureReceive(187, ManufactureType.OIL.name, 0.01 )
            setBody(respond)
        }.apply {
            assertEquals(HttpStatusCode.OK, this.status)
        }
    }

    @Test
    fun testDeleteManufactureCountryid() = testApplication {
        application {}
        val client = HttpClient()
        client.delete("http://localhost/manufacture/187").apply {
            assertEquals(HttpStatusCode.OK, this.status)
        }
    }
}