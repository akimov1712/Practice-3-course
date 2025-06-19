package ru.donnu.practice.feature.country

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class CountryRoutingKtTest {

    @Test
    fun testGetCountry() = testApplication {
        application {}
        val client = HttpClient()
        client.get("http://localhost/country").apply {
            assertEquals(HttpStatusCode.OK, this.status)
        }
    }
}