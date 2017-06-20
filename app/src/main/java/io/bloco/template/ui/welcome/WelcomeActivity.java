package io.bloco.template.ui.welcome;

import android.os.Bundle;
import butterknife.BindView;
import io.bloco.template.R;
import io.bloco.template.data.models.Card;
import io.bloco.template.ui.BaseActivity;
import java.util.List;
import javax.inject.Inject;

public class WelcomeActivity extends BaseActivity implements WelcomePresenter.View {

  @Inject WelcomePresenter presenter;

  @BindView(R.id.welcome_cards) WelcomeView welcomeView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getActivityComponent().inject(this);
    presenter.start(this);
  }

  @Override protected int getLayoutRes() {
    return R.layout.activity_welcome;
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.stop();
  }

  @Override public void showCards(List<Card> cards) {
    String welcomeText = "";
    for (Card card : cards) {
      welcomeText += card.getName() + "\n";
    }
    welcomeView.setText(welcomeText);
  }
}
