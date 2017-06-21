package io.bloco.template.ui;

import android.arch.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

  private CompositeDisposable compositeDisposable;

  public BaseViewModel() {
    compositeDisposable = new CompositeDisposable();
  }

  public void addDisposables(Disposable... disposables) {
    compositeDisposable.addAll(disposables);
  }

  @Override protected void onCleared() {
    super.onCleared();
    compositeDisposable.clear();
  }
}
