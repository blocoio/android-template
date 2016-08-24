package io.bloco.template.common.di;

import android.content.Context;
import android.content.res.Resources;
import dagger.Module;
import dagger.Provides;
import io.bloco.template.AndroidApplication;

@Module public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @PerApplication public Context provideApplicationContext() {
    return application;
  }

  @Provides @PerApplication public AndroidApplication.Mode provideApplicationMode() {
    return application.getMode();
  }

  @Provides @PerApplication public Resources provideResources(Context context) {
    return context.getResources();
  }
}
