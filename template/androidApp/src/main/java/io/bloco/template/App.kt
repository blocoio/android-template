package io.bloco.template

import android.app.Application
import android.os.StrictMode
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import io.bloco.core.commons.Log.Level
import io.bloco.core.commons.Log.Logger
import io.bloco.core.commons.Log.addLogger

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
        addLogger(object : Logger {
            override fun log(
                level: Level,
                tag: String?,
                message: String,
                throwable: Throwable?
            ) {
                val androidLogLevel = when (level) {
                    Level.ERROR -> Log.ERROR
                    Level.WARN -> Log.WARN
                    Level.INFO -> Log.INFO
                    Level.DEBUG -> Log.DEBUG
                    Level.VERBOSE -> Log.VERBOSE
                }

                when (androidLogLevel) {
                    Log.ERROR -> Log.e(tag, message, throwable)
                    Log.WARN -> Log.e(tag, message, throwable)
                    else -> Log.println(androidLogLevel, tag, message)
                }
            }
        })
    }
}
