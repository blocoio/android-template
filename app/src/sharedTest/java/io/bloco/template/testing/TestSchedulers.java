package io.bloco.template.testing;

import io.bloco.template.common.Schedulers;
import rx.Scheduler;

public class TestSchedulers extends Schedulers {

  @Override public Scheduler getMainThread() {
    return rx.schedulers.Schedulers.immediate();
  }

  @Override public Scheduler getBackgroundThread() {
    return rx.schedulers.Schedulers.immediate();
  }
}
