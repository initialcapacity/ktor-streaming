package io.initialcapacity.streaming

import io.initialcapacity.streaming.messages.MessageProvider
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

fun Application.module(delay: Duration) {
    val provider = MessageProvider(delay)
    routing {
        index(provider)
        staticResources("images", "images")
        staticResources("styles", "styles")
    }
}

fun main() {
    embeddedServer(Netty, port = 8080) {
        module(2.seconds)
    }.start(wait = true)
}
