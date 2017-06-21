package io.bloco.template.common.di.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import io.bloco.template.ui.welcome.WelcomeViewModel;

@Module
public abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(WelcomeViewModel.class)
  abstract ViewModel bindUserViewModel(WelcomeViewModel welcomeViewModel);

  @Binds
  abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}