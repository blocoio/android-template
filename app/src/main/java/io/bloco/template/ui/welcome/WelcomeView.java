package io.bloco.template.ui.welcome;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import io.bloco.template.R;
import io.bloco.template.ui.BaseView;

public class WelcomeView extends BaseView {

  @BindView(R.id.welcome_text) TextView textView;

  public WelcomeView(Context context, AttributeSet attrs) {
    super(context, attrs);
    getViewComponent().inject(this);
  }

  @Override protected int getLayoutRes() {
    return R.layout.view_welcome_text;
  }

  public void setText(String text) {
    textView.setText(text);
  }
}
