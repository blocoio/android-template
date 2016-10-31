package io.bloco.template.common;

import io.bloco.template.common.di.PerApplication;
import javax.inject.Inject;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

@PerApplication public class Schedulers {

  @Inject public Schedulers() {
  }

  public Scheduler getMainThread() {
    return AndroidSchedulers.mainThread();
  }

  public Scheduler getBackgroundThread() {
    return rx.schedulers.Schedulers.io();
  }

  public <T> Observable.Transformer<T, T> apply() {
    return new Observable.Transformer<T, T>() {
      @Override public Observable<T> call(Observable<T> observable) {
        return observable.subscribeOn(getBackgroundThread()).observeOn(getMainThread());
      }
    };
  }
}
