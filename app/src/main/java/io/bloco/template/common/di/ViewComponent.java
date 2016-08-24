package io.bloco.template.common.di;

import android.view.View;
import dagger.Component;

@PerView @Component(dependencies = ActivityComponent.class, modules = ViewModule.class)
public interface ViewComponent {

  View view();

  // Views

}