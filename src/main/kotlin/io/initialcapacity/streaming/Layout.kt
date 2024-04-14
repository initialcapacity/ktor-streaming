package io.initialcapacity.streaming

import io.ktor.server.html.*
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.Writer

class Layout : Template<HTML> {
    val content = Placeholder<FlowContent>()

    override fun HTML.apply() {
        head {
            meta(charset = "UTF-8")
            meta(
                name = "viewport",
                content = "width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
            )

            applicationStyles()
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
                    li { h1 { +"Streaming HTML" } }
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
                    script(type = ScriptType.textJavaScript) {
                        unsafe {
                            raw("""document.write("©" + new Date().getFullYear());""")
                        }
                    }
                    +"Initial Capacity, Inc."
                }
            }
            main {
                insert(content)
            }
        }
    }

}

fun FlowOrMetaDataOrPhrasingContent.applicationStyles() {
    link(rel = "stylesheet", href = "/styles/application.css")
}

fun Writer.layout(content: FlowContent.() -> Unit) {
    val template = Layout()
    template.content { content() }

    appendHTML().html {
        with(template) { apply() }
    }
}
