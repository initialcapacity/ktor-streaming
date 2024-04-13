package io.initialcapacity.streaming.waitfor

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import java.io.Writer

fun <T> Writer.waitFor(deferred: Deferred<T>): T {
    flush()
    return runBlocking { deferred.await() }
}
