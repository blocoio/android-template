package io.bloco.template.common.di;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;

@Module public class ActivityModule {
  private final Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity Activity activity() {
    return this.activity;
  }
}
