package io.bloco.template.common.di.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import io.bloco.template.common.di.PerApplication;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;

@PerApplication
public class ViewModelFactory implements ViewModelProvider.Factory {

  private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

  @Inject
  public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
    this.creators = creators;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    Provider<? extends ViewModel> creator = creators.get(modelClass);
    if (creator == null) {
      for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
        if (modelClass.isAssignableFrom(entry.getKey())) {
          creator = entry.getValue();
          break;
        }
      }
    }
    if (creator == null) {
      throw new IllegalArgumentException("unknown model class " + modelClass);
    }
    try {
      return (T) creator.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
