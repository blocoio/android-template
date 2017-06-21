package io.bloco.template.common.di.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import io.bloco.template.common.di.PerActivity;
import javax.inject.Inject;

@PerActivity
public class ActivityViewModelProvider {

  private final ViewModelProvider viewModelProvider;

  @Inject
  public ActivityViewModelProvider(FragmentActivity activity, ViewModelProvider.Factory factory) {
    viewModelProvider = ViewModelProviders.of(activity, factory);
  }

  public <T extends ViewModel> T get(Class<T> viewModelClass) {
    return viewModelProvider.get(viewModelClass);
  }
}
