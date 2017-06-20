package io.bloco.template.helpers;

import android.support.test.InstrumentationRegistry;
import io.bloco.template.AndroidApplication;
import io.bloco.template.common.di.ApplicationComponent;

public class ApplicationHelper {
  public static AndroidApplication getApplication() {
    return (AndroidApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
  }

  public static ApplicationComponent getApplicationComponent() {
    return getApplication().getApplicationComponent();
  }
}
