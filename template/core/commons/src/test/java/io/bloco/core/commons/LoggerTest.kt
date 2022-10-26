package io.bloco.core.commons

import io.bloco.core.commons.Log.addLogger
import io.bloco.core.commons.Log.clearLoggers
import junit.framework.Assert.assertEquals
import org.junit.Test

class LoggerTest {

    private val logList = mutableListOf<String>()

    private val customTestLogger = object : Log.Logger {
        override fun log(level: Log.Level, tag: String?, message: String, throwable: Throwable?) {
            logList.add("$level $tag $message $throwable")
        }
    }

    @Test
    fun `Add Logger and test util functions`() {
        addLogger(customTestLogger)

        val message = "example"
        val error = Throwable(message)

        logd(message)
        assertEquals(logList.size, 1)
        assertEquals(logList[0], "${Log.Level.DEBUG} LoggerKt $message null")

        logv(message)
        assertEquals(logList.size, 2)
        assertEquals(logList[1], "${Log.Level.VERBOSE} LoggerKt $message null")

        logi(message)
        assertEquals(logList.size, 3)
        assertEquals(logList[2], "${Log.Level.INFO} LoggerKt $message null")

        logw(message)
        assertEquals(logList.size, 4)
        assertEquals(logList[3], "${Log.Level.WARN} LoggerKt $message null")

        logw(message, error)
        assertEquals(logList.size, 5)
        assertEquals(logList[4], "${Log.Level.WARN} LoggerKt $message $error")

        loge(message)
        assertEquals(logList.size, 6)
        assertEquals(logList[5], "${Log.Level.ERROR} LoggerKt $message null")

        loge(message, error)
        assertEquals(logList.size, 7)
        assertEquals(logList[6], "${Log.Level.ERROR} LoggerKt $message $error")
    }

    @Test
    fun `Clean Logger`() {
        addLogger(customTestLogger)

        val message = "example"

        logd(message)
        assertEquals(logList.size, 1)
        assertEquals(logList[0], "${Log.Level.DEBUG} LoggerKt $message null")

        clearLoggers()
        logd(message)
        assertEquals(logList.size, 1)
        assertEquals(logList[0], "${Log.Level.DEBUG} LoggerKt $message null")
    }
}
