package io.initialcapacity.streaming.templatesupport

import kotlinx.html.*

class SLOT(name: String, consumer: TagConsumer<*>) :
    HTMLTag("slot", consumer, mapOf("name" to name), inlineTag = false, emptyTag = false), HtmlBlockTag

fun SectioningOrFlowContent.slot(name: String, block: SLOT.() -> Unit = {}) {
    SLOT(name, consumer).visit(block)
}
