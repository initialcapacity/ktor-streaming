package io.initialcapacity.streaming

import kotlinx.html.*

class USE(attributes: Map<String, String>, consumer: TagConsumer<*>) :
    HTMLTag("use", consumer, attributes, inlineTag = false, emptyTag = false), HtmlBlockTag

fun SVG.useTag(xlinkHref: String, block: USE.() -> Unit = {}) {
    USE(mapOf("xlink:href" to xlinkHref), consumer).visit(block)
}
