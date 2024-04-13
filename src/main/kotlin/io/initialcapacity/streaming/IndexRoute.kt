package io.initialcapacity.streaming

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import kotlin.time.Duration

fun Route.index(delay: Duration) {
    get("/") {
        call.respondTextWriter(ContentType.Text.Html, HttpStatusCode.OK) {
            layout {
                template {
                    applicationStyles()

                    section {
                        h1 {
                            +"Streaming HTML"
                        }
                        p {
                            +"An example showing "
                            a("https://developer.mozilla.org/en-US/docs/Web/API/Response/body") { +"streaming responses" }
                            +" to the "
                            a("https://developer.mozilla.org/en-US/docs/Web/API/ShadowRoot/mode") { +"Shadow DOM" }
                            +" from a Ktor server. Enables async DOM updates with no Javascript needed!"
                        }
                    }
                    section {
                        slot("content") {
                            h2 { +"Wait for it..." }
                        }
                    }
                }
                flush()
                Thread.sleep(delay.inWholeMilliseconds)
                div {
                    attributes["slot"] = "content"
                    h2 { +"Success!" }
                }
            }
        }
    }
}