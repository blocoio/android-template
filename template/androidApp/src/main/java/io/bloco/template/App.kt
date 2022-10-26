package io.bloco.template

import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp
import io.bloco.core.commons.Log.addLogger
import io.bloco.template.utils.AndroidLogger

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
        addLogger(AndroidLogger())
    }
}
