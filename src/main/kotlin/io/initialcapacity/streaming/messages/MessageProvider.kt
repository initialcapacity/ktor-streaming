package io.initialcapacity.streaming.messages

import kotlinx.coroutines.delay
import kotlin.time.Duration

class MessageProvider(private val delay: Duration) {
    suspend fun fetchAll(): List<String> {
        delay(delay)

        return listOf("Here's some slow content.", "It took a while to load.", "And didn't use any javascript.")
    }
}
