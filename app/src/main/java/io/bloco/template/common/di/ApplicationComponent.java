package io.bloco.template.common.di;

import android.content.Context;
import android.content.res.Resources;
import dagger.Component;
import io.bloco.template.data.Database;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  //Exposed to sub-graphs.
  Context context();

  Resources resources();

  Database database();
}
