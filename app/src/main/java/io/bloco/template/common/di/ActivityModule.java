package io.bloco.template.common.di;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
import io.bloco.template.presentation.welcome.WelcomeContract;
import io.bloco.template.presentation.welcome.WelcomePresenter;

@Module public class ActivityModule {
  private final Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity Activity activity() {
    return this.activity;
  }

  @Provides @PerActivity
  public WelcomeContract.Presenter provideWelcomePresenter(WelcomePresenter presenter) {
    return presenter;
  }
}
