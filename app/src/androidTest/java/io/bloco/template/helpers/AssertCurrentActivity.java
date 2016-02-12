package io.bloco.template.helpers;

import android.app.Activity;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;

public class AssertCurrentActivity {
  public static void assertCurrentActivity(Class<? extends Activity> activityClass) {
    assertEquals(activityClass.getName(), getCurrentActivity().getComponentName().getClassName());
  }

  private static Activity getCurrentActivity() {
    getInstrumentation().waitForIdleSync();
    final Activity[] activity = new Activity[1];
    getInstrumentation().runOnMainSync(new Runnable() {
      @Override public void run() {
        Collection<Activity> activities =
            ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
        activity[0] = activities.iterator().next();
      }
    });
    return activity[0];
  }
}
