package io.initialcapacity.streaming.templatesupport

import kotlinx.html.*

class TEMPLATE(consumer: TagConsumer<*>) :
    HTMLTag("template", consumer, mapOf("shadowrootmode" to "open"), inlineTag = false, emptyTag = false), HtmlBlockTag

fun SectioningOrFlowContent.template(block: TEMPLATE.() -> Unit = {}) {
    TEMPLATE(consumer).visit(block)
}
