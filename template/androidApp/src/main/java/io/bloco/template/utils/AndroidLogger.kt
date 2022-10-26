package io.bloco.template.utils

import io.bloco.core.commons.Log

class AndroidLogger : Log.Logger {
    override fun log(
        level: Log.Level,
        tag: String?,
        message: String,
        throwable: Throwable?
    ) {
        val androidLogLevel = when (level) {
            Log.Level.ERROR -> android.util.Log.ERROR
            Log.Level.WARN -> android.util.Log.WARN
            Log.Level.INFO -> android.util.Log.INFO
            Log.Level.DEBUG -> android.util.Log.DEBUG
            Log.Level.VERBOSE -> android.util.Log.VERBOSE
        }

        when (androidLogLevel) {
            android.util.Log.ERROR -> android.util.Log.e(tag, message, throwable)
            android.util.Log.WARN -> android.util.Log.e(tag, message, throwable)
            else -> android.util.Log.println(androidLogLevel, tag, message)
        }
    }
}
