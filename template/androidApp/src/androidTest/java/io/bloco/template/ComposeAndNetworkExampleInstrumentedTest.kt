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
import io.bloco.core.commons.endpoints.OpenLibraryEndpoint
import io.bloco.datatest.DataTestResources
import io.bloco.datatest.MockOpenLibraryApi
import io.ktor.http.*
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
    private lateinit var instrumentationContext: Context

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
    fun listBooksSuccess() {
        MockOpenLibraryApi.giveResponse(
            request = OpenLibraryEndpoint.search("Android"),
            response = MockOpenLibraryApi.ResponseValue(
                statusCode = HttpStatusCode.OK,
                content = DataTestResources.bookListJson()
            )
        )

        with(composeRule) {
            waitForIdle()
            /**
             * Compose flattens its UI tree, so some elements can be combined into a single
             * Composable. This process happens in a later stage so to use the first pre-draw tree
             * useUnmergedTree should be true.
             */
            onNodeWithText(
                instrumentationContext.getString(R.string.book_list),
                useUnmergedTree = true
            )
                .assertIsDisplayed()

            onAllNodesWithText("bloco.io")
                .assertCountEquals(5)
        }
    }

    @Test
    fun listBooksError() {
        MockOpenLibraryApi.giveResponse(
            request = OpenLibraryEndpoint.search("Android"),
            response = MockOpenLibraryApi.ResponseValue(
                statusCode = HttpStatusCode.BadRequest
            )
        )

        with(composeRule) {
            waitForIdle()
            onNodeWithText(instrumentationContext.getString(R.string.book_list))
                .assertDoesNotExist()
        }
    }
}
