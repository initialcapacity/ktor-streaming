package test.initialcapacity.streaming.messages

import io.initialcapacity.streaming.messages.MessageProvider
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration

class MessageProviderTest {
    @Test
    fun testFetchAll() = runBlocking {
        assertEquals(
            listOf("Here's some slow content.", "It took a while to load.", "And didn't use any javascript."),
            MessageProvider(Duration.ZERO).fetchAll()
        )
    }
}