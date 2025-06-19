package ru.donnu.practice.feature.production

import entity.ProductionEntity
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class ProductionRoutingKtTest {

    @Test
    fun testGetProduction() = testApplication {
        application {}
        val client = HttpClient()
        client.get("http://localhost/production").apply {
            assertEquals(HttpStatusCode.OK, this.status)
        }
    }
}