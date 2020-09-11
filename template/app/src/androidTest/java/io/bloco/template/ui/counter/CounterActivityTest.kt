package io.bloco.template.ui.counter

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.bloco.template.R
import io.bloco.template.utils.ClearPreferencesTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CounterActivityTest {

    @get:Rule
    val clearPreferencesTestRule = ClearPreferencesTestRule()

    @get:Rule
    val intentTestRule = ActivityScenarioRule<CounterActivity>(CounterActivity::class.java)

    @Test
    fun testIncrementAndDecrementButtons() {
        onView(withId(R.id.fabIncrement)).perform(click())
        onView(withId(R.id.value)).check(matches(withText("1")))

        onView(withId(R.id.fabDecrement)).perform(click())
        onView(withId(R.id.value)).check(matches(withText("0")))
    }

    @Test
    fun decrementErrorMessage() {
        onView(withId(R.id.fabDecrement)).perform(click())
        onView(withId(R.id.value)).check(matches(withText("0")))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.error_message)))
    }

}