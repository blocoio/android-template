package io.bloco.template

import android.content.Context
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.bloco.data_test.MockOpenLibraryApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ComposeAndNetworkExampleInstrumentedTest {

    private val hiltRule = HiltAndroidRule(this)
    private val composeRule = createEmptyComposeRule()

    private lateinit var scenario: ActivityScenario<MainActivity>
    lateinit var instrumentationContext: Context

    @get:Rule
    val chain: TestRule = RuleChain
        .outerRule(hiltRule)
        .around(composeRule)

    @Before
    fun setUp() {
        hiltRule.inject()
        scenario = ActivityScenario.launch(MainActivity::class.java)
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun listBooksSuccess()  {
        MockOpenLibraryApi.giveSuccess()
        with(composeRule) {
            waitForIdle()
            onNodeWithText(instrumentationContext.getString(R.string.book_list), useUnmergedTree = true)
                .assertIsDisplayed()

            onAllNodesWithText("bloco.io")
                .assertCountEquals(10)
        }
    }

    @Test
    fun listBooksError() {
        MockOpenLibraryApi.giveInternetError()

        with(composeRule) {
            waitForIdle()
            onNodeWithText(instrumentationContext.getString(R.string.book_list))
                .assertDoesNotExist()
        }
    }

}