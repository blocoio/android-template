package io.bloco.template;

import android.app.Application;
import android.os.StrictMode;
import io.bloco.template.common.di.ApplicationComponent;
import io.bloco.template.common.di.ApplicationModule;
import io.bloco.template.common.di.DaggerApplicationComponent;
import timber.log.Timber;

public class AndroidApplication extends Application {

  private Mode mode;
  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    checkTestMode();
    this.initializeInjector();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
      configStrictMode();
    }
  }

  public ApplicationComponent getApplicationComponent() {
    return this.applicationComponent;
  }

  public Mode getMode() {
    return mode;
  }

  private void initializeInjector() {
    this.applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
  }

  private void configStrictMode() {
    StrictMode.setThreadPolicy(
        new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().penaltyDialog().build());
    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
  }

  // Test loading a random test class, to check if we're in test mode
  private void checkTestMode() {
    try {
      getClassLoader().loadClass("io.bloco.template.ApplicationTest");
      mode = Mode.TEST;
    } catch (final Exception e) {
      mode = Mode.NORMAL;
    }
  }

  public enum Mode {
    NORMAL, TEST
  }
}