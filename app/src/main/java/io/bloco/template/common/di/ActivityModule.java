package io.bloco.template.common.di;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;

@Module public class ActivityModule {
  private final AppCompatActivity activity;

  public ActivityModule(AppCompatActivity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity Activity activity() {
    return this.activity;
  }

  @Provides @PerActivity FragmentActivity fragmentActivity() {
    return this.activity;
  }
}
