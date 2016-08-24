package io.bloco.template.common.di;

import android.view.View;
import dagger.Module;
import dagger.Provides;

@Module public class ViewModule {
  private final View view;

  public ViewModule(View view) {
    this.view = view;
  }

  @Provides @PerView View view() {
    return this.view;
  }
}
