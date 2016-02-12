package io.bloco.template.presentation;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import io.bloco.template.AndroidApplication;
import io.bloco.template.R;
import io.bloco.template.common.Preconditions;
import io.bloco.template.common.di.ActivityModule;
import io.bloco.template.common.di.ApplicationComponent;

public class BaseActivity extends AppCompatActivity {

  protected Toolbar toolbar;

  protected ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication) getApplication()).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }

  protected void setupToolbar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    Preconditions.checkNotNull(toolbar, "Toolbar view not found");
    setSupportActionBar(toolbar);
  }
}
