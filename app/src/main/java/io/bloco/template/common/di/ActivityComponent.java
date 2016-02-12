package io.bloco.template.common.di;

import android.app.Activity;
import dagger.Component;
import io.bloco.template.presentation.welcome.WelcomeActivity;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(WelcomeActivity activity);

  //Exposed to sub-graphs.
  Activity activity();
}
