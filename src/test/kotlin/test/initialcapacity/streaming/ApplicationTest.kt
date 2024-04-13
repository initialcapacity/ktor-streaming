package test.initialcapacity.streaming

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testIndex() = testApp { client ->
        val response = client.get("/")

        assertEquals(HttpStatusCode.OK, response.status)
        assertContains(response.bodyAsText(), "Streaming HTML")
        assertContains(response.bodyAsText(), "More content")
    }
}