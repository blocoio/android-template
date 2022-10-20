
package io.bloco.template

import io.bloco.core.commons.logDebug
import io.bloco.core.commons.setup
import org.junit.Test

import org.junit.Assert.assertEquals
import java.util.logging.Level

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    init {
        io.bloco.core.commons.RootLogger.setup({ _: Level, _: String, message: String, _: Throwable? ->
            println(message)
        }, true)
    }

    @Test
    fun addition_isCorrect() {
        logDebug { "something" }
        assertEquals(4, 2 + 2)
    }
}