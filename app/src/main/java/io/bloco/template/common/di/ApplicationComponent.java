package io.bloco.template.common.di;

import dagger.Component;

@PerApplication @Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  ActivityComponent plus(ActivityModule activityModule);
}
