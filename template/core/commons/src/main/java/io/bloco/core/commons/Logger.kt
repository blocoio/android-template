package io.bloco.core.commons

import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.LogRecord
import java.util.logging.Logger
import java.util.regex.Pattern

private var allowDebugLog: Boolean = false
private var loggers = emptyList<TemplateLogger>()

inline val RootLogger: Logger
    get() = LogManager.getLogManager().getLogger("")

private val Any.logger: Logger
    get() = Logger.getLogger(findTag())

// From the Timber library
private fun findTag(): String =
    Throwable().stackTrace
        .first { it.className !in fqcnIgnore }
        .let(::createStackElementTag)

private val fqcnIgnore = listOf(
    "io.bloco.core.commons.LoggerKt",
    Logger::class.java.name
)

private fun createStackElementTag(element: StackTraceElement): String {
    val maxTagLenght = 23
    var tag = element.className.substringAfterLast('.')

    val m = Pattern.compile("(\\$\\d+)+$").matcher(tag)
    if (m.find()) {
        tag = m.replaceAll("")
    }
    return tag.take(maxTagLenght)
}


fun Logger.setup(onPublish: (level: Level, tag: String, message: String, error: Throwable?) -> Unit, allowDebug: Boolean = false) {
    val handler = object : Handler() {
        override fun publish(record: LogRecord) {
            onPublish(record.level, record.loggerName, record.message, record.thrown)
        }
        override fun flush() {}
        override fun close() {}
    }

    for (h in RootLogger.handlers) {
        removeHandler(h)
    }
    addHandler(handler)
    allowDebugLog = allowDebug
    level = Level.FINE
}

fun addLogTree(logger: TemplateLogger) {
    loggers = loggers + logger
}

interface TemplateLogger {
    fun logDebug(message: String)
    fun logWarning(message: String)
    fun logInfo(message: String)
    fun logError(throwable: Throwable, message: String)
}

fun Any.logDebug(message: () -> String) {
    if (allowDebugLog) {
        loggers.forEach {
            it.logDebug(message())
        }
        logger.log(Level.FINE, message())
    }
}

fun Any.logWarning(message: () -> String) {
    loggers.forEach { it.logWarning(message()) }
    logger.log(Level.WARNING, message())
}

fun Any.logInfo(message: () -> String) {
    loggers.forEach { it.logInfo(message()) }
    logger.log(Level.INFO, message())
}

fun Any.logError(throwable: Throwable, message: () -> String) {
    loggers.forEach { it.logError(throwable, message()) }
    logger.log(Level.SEVERE, message(), throwable)
}

