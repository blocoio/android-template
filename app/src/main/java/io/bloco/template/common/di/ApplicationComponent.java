package io.bloco.template.common.di;

import android.content.Context;
import android.content.res.Resources;
import dagger.Component;
import io.bloco.template.AndroidApplication;
import io.bloco.template.data.Database;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {
  Context context();

  AndroidApplication.Mode applicationMode();

  Resources resources();

  Database database();
}
