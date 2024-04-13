package test.initialcapacity.streaming

import io.initialcapacity.streaming.module
import io.ktor.client.*
import io.ktor.server.testing.*
import kotlin.time.Duration

fun testApp(test: suspend (client: HttpClient) -> Unit) = testApplication {
    application { module(Duration.ZERO) }
    test(createClient {})
}
