package io.bloco.template.common;

import io.bloco.template.common.di.PerApplication;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

@PerApplication public class RxSchedulers {

  @Inject public RxSchedulers() {
  }

  public Scheduler getMainThread() {
    return AndroidSchedulers.mainThread();
  }

  public Scheduler getBackgroundThread() {
    return Schedulers.io();
  }

  public <T> ObservableTransformer<T, T> apply() {
    return observable -> observable
        .subscribeOn(getBackgroundThread())
        .observeOn(getMainThread());
  }
}
