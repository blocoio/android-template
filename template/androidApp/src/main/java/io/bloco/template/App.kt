package io.bloco.template

import android.app.Application
import android.os.StrictMode
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import io.bloco.core.commons.RootLogger
import io.bloco.core.commons.TemplateLogger
import io.bloco.core.commons.addLogTree
import io.bloco.core.commons.logDebug
import io.bloco.core.commons.logError
import io.bloco.core.commons.logInfo
import io.bloco.core.commons.logWarning
import io.bloco.core.commons.setup
import java.util.logging.Level


@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupLogger()
        setupStrictMode()
    }

    private fun setupStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }
    }

    private fun setupLogger() {
        RootLogger.setup({ level: Level, tag: String, message: String, error: Throwable? ->
            val androidLogLevel = when (level.intValue()) {
                Level.SEVERE.intValue() -> Log.ERROR
                Level.WARNING.intValue() -> Log.WARN
                Level.INFO.intValue() -> Log.INFO
                else -> Log.DEBUG
            }

            error?.let {
                Log.e(tag, message, error)
            } ?: Log.println(androidLogLevel, tag, message)
        }, BuildConfig.DEBUG)
    }

}