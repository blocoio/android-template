package io.bloco.template.presentation.welcome;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import io.bloco.template.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static io.bloco.template.helpers.AssertCurrentActivity.assertCurrentActivity;

@RunWith(AndroidJUnit4.class)
public class WelcomeActivityTest {

  @Rule public ActivityTestRule<WelcomeActivity> activityTestRule =
      new ActivityTestRule<>(WelcomeActivity.class);

  @Test public void testActivityStarted() throws Exception {
    activityTestRule.getActivity();
    assertCurrentActivity(WelcomeActivity.class);
  }

  @Test public void testTitle() throws Exception {
    onView(withText(R.string.app_name)).check(matches(isDisplayed()));
  }

  @Test public void testCards() throws Exception {
    onView(withText("John Smith\n")).check(matches(isDisplayed()));
  }
}
