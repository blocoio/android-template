package io.bloco.template.common.di.viewmodel;

import android.arch.lifecycle.ViewModel;
import dagger.MapKey;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
@interface ViewModelKey {
  Class<? extends ViewModel> value();
}