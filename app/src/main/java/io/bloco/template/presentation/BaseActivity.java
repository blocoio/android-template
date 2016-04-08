package io.bloco.template.presentation;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import io.bloco.template.AndroidApplication;
import io.bloco.template.R;
import io.bloco.template.common.di.ActivityComponent;
import io.bloco.template.common.di.ActivityModule;
import io.bloco.template.common.di.ApplicationComponent;
import io.bloco.template.common.di.DaggerActivityComponent;

public abstract class BaseActivity extends AppCompatActivity {

  protected Toolbar toolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutRes());
    ButterKnife.bind(this);
    setupToolbar();
  }

  protected ActivityComponent getActivityComponent() {
    return DaggerActivityComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @LayoutRes protected abstract int getLayoutRes();

  // Private

  private ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication) getApplication()).getApplicationComponent();
  }

  private ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }

  private void setupToolbar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);
    }
  }
}
