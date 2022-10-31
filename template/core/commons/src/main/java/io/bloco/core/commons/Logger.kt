@file:Suppress("MatchingDeclarationName", "MemberNameEqualsClassName")
package io.bloco.core.commons

import java.util.regex.Pattern

object Log {

    private val loggers = mutableListOf<Logger>()

    fun addLogger(logger: Logger) {
        loggers.remove(DEFAULT_LOGGER)
        loggers.add(logger)
    }

    fun clearLoggers() {
        loggers.clear()
    }

    fun log(level: Level, message: String, throwable: Throwable?) {
        val tag = findTag()
        loggers.forEach {
            it.log(level, tag, message, throwable)
        }
    }

    private val DEFAULT_LOGGER = object : Logger {
        override fun log(level: Level, tag: String?, message: String, throwable: Throwable?) {
            java.util.logging.Logger.getGlobal().log(
                when (level) {
                    Level.ERROR -> java.util.logging.Level.SEVERE
                    Level.WARN -> java.util.logging.Level.WARNING
                    Level.INFO -> java.util.logging.Level.INFO
                    Level.DEBUG -> java.util.logging.Level.FINE
                    Level.VERBOSE -> java.util.logging.Level.FINEST
                },
                tag?.let { "$tag: $message" } ?: message,
                throwable
            )
        }
    }

    interface Logger {
        fun log(level: Level, tag: String?, message: String, throwable: Throwable?)
    }

    enum class Level(val priority: Int) {
        ERROR(6),
        WARN(5),
        INFO(4),
        DEBUG(3),
        VERBOSE(2)
    }

    // From the Timber library

    private fun findTag(): String =
        Throwable().stackTrace
            .first { it.className !in fqcnIgnore }
            .let(::createStackElementTag)

    private val fqcnIgnore = listOf(
        Log::class.java.name,
        Log::class.java.name + "Kt",
        Logger::class.java.name,
    )

    private fun createStackElementTag(element: StackTraceElement): String {
        var tag = element.className.substringAfterLast('.')
        val m = ANONYMOUS_CLASS.matcher(tag)
        if (m.find()) {
            tag = m.replaceAll("")
        }
        return tag.take(MAX_TAG_LENGTH)
    }
    val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")
    private const val MAX_TAG_LENGTH = 23
}

fun logv(message: String, throwable: Throwable? = null) =
    Log.log(Log.Level.VERBOSE, message, throwable)

fun logd(message: String, throwable: Throwable? = null) =
    Log.log(Log.Level.DEBUG, message, throwable)

fun logi(message: String, throwable: Throwable? = null) =
    Log.log(Log.Level.INFO, message, throwable)

fun logw(message: String, throwable: Throwable? = null) =
    Log.log(Log.Level.WARN, message, throwable)

fun loge(message: String, throwable: Throwable? = null) =
    Log.log(Log.Level.ERROR, message, throwable)
