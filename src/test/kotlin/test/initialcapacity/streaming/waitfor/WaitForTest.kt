package test.initialcapacity.streaming.waitfor

import io.initialcapacity.streaming.waitfor.waitFor
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.Writer
import kotlin.test.Test
import kotlin.test.assertEquals

class WaitForTest {
    @Test
    fun testWaitFor() = runBlocking {
        val messages = Channel<String>()
        val message = async { messages.receive() }
        val writer = TestWriter()

        writer.write("Start")
        val waited = async { writer.write(writer.waitFor(message)) }

        messages.send("End")
        waited.await()
        assertEquals(listOf("Start", "flush", "End"), writer.actions)
    }
}

class TestWriter : Writer() {
    val actions = mutableListOf<String>()

    override fun close() {
        actions.addLast("close")
    }

    override fun flush() {
        actions.addLast("flush")
    }

    override fun write(cbuf: CharArray, off: Int, len: Int) {
        actions.addLast(String(cbuf, off, len))
    }
}
