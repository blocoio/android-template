package io.bloco.template.utils

import androidx.preference.PreferenceManager
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.rules.ExternalResource

class ClearPreferencesTestRule : ExternalResource() {

    @Throws(Throwable::class)
    override fun before() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().commit()
    }

}


