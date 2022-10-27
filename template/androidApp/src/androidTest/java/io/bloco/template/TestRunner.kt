package io.bloco.template

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication
import io.bloco.core.commons.Log.addLogger
import io.bloco.template.utils.AndroidLogger

class TestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        addLogger(AndroidLogger())
        return super.newApplication(cl, HiltTestApplication::class.java.canonicalName, context)
    }
}
