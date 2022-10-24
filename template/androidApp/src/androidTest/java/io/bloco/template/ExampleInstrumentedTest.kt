package io.bloco.template

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.bloco.core.commons.logd

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.assertEquals
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        logd("App Package name = ${appContext.packageName}")
        assertEquals("io.bloco.template", appContext.packageName)
    }
    
}