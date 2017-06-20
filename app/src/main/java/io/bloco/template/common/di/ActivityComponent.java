package io.bloco.template.common.di;

import dagger.Subcomponent;
import io.bloco.template.ui.welcome.WelcomeActivity;

@PerActivity @Subcomponent(modules = ActivityModule.class) public interface ActivityComponent {
  ViewComponent plus(ViewModule viewModule);

  void inject(WelcomeActivity activity);
}
