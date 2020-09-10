package io.bloco.template

import android.app.Application
import android.os.Build
import android.os.StrictMode
import io.bloco.template.common.di.AppComponent
import io.bloco.template.common.di.DaggerAppComponent

open class App : Application() {

    val mode by lazy {
        try {
            classLoader.loadClass("io.bloco.courier.AppTest")
            Mode.Test
        } catch (e: ClassNotFoundException) {
            Mode.Normal
        }
    }

    open val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        setupStrictMode()
    }

    private fun setupStrictMode() {
        if (BuildConfig.DEBUG && mode != Mode.Test) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().penaltyDeath().build()
            )
            StrictMode.setVmPolicy(
                /*
                  To disable the some of the checks we need to manually set all checks.
                  This code is based on the `detectAll()` implementation.
                  Checks disabled:
                  - UntaggedSockets (we aren't able to tag Netty socket threads)
                  - CleartextNetwork (it's considering gRPC over TLS communication as cleartext)
                 */
                StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectActivityLeaks()
                    .detectLeakedClosableObjects()
                    .detectLeakedRegistrationObjects()
                    .detectFileUriExposure()
                    .apply {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            detectContentUriWithoutPermission()
                        }
                    }
                    .apply {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            detectCredentialProtectedWhileLocked()
                        }
                    }
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
    }

    enum class Mode { Normal, Test }

}