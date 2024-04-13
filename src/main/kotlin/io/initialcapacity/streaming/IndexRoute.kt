package io.initialcapacity.streaming

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.h1
import kotlinx.html.p
import kotlinx.html.section
import kotlin.time.Duration

fun Route.index(delay: Duration) {
    get("/") {
        call.respondTextWriter(ContentType.Text.Html, HttpStatusCode.OK) {
            layout {
                section {
                    h1 {
                        +"Streaming HTML"
                    }
                    p {
                        flush()
                        Thread.sleep(delay.inWholeMilliseconds)
                        +"More content"
                    }
                }
            }
        }
    }
}