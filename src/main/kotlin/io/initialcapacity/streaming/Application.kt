package io.initialcapacity.streaming

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.staticResources
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondTextWriter
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

fun Application.module(delay: Duration) {
    routing {
        get("/") {
            call.respondTextWriter(ContentType.Text.Html, HttpStatusCode.OK) {
                appendHTML().html {
                    head {
                        meta(charset = "UTF-8")
                        meta(
                            name = "viewport",
                            content = "width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
                        )

                        link(rel = "stylesheet", href = "/styles/application.css")
                        link(rel = "icon", href = "/images/favicon.ico") { sizes = "48x48" }
                        link(rel = "icon", href = "/images/favicon.svg") { sizes = "any"; type = "image/svg+xml" }

                        title {
                            +"Streaming HTML"
                        }
                    }
                    body {
                        header {
                            ul {
                                li {
                                    svg {
                                        useTag("/images/icons.svg#logo")
                                    }
                                }
                                li { +"Streaming HTML" }
                            }
                        }
                        footer {
                            ul {
                                li {
                                    a("https://github.com/initialcapacity/ktor-streaming") { +"View on GitHub" }
                                }
                                li {
                                    a("https://initialcapacity.io") { +"Initial Capacity" }
                                }
                            }
                            span {
                                +"©"
                                script { +"""document.write(new Date().getFullYear());""" }
                                +"Initial Capacity, Inc."
                            }
                        }
                        main {
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
        }
        staticResources("images", "images")
        staticResources("styles", "styles")
    }
}

fun main() {
    embeddedServer(Netty, port = 8080) {
        module(2.seconds)
    }.start(wait = true)
}
