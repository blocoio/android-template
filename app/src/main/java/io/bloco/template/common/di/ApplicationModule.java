package io.bloco.template.common.di;

import android.content.Context;
import android.content.res.Resources;
import dagger.Module;
import dagger.Provides;
import io.bloco.template.AndroidApplication;
import javax.inject.Singleton;

@Module public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton public Context provideApplicationContext() {
    return application;
  }

  @Provides @Singleton public AndroidApplication.Mode provideApplicationMode() {
    return application.getMode();
  }

  @Provides @Singleton public Resources provideResources(Context context) {
    return context.getResources();
  }
}
