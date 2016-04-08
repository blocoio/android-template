package io.bloco.template.presentation.welcome;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import io.bloco.template.R;
import io.bloco.template.data.models.Card;
import io.bloco.template.presentation.BaseActivity;
import java.util.List;
import javax.inject.Inject;

public class WelcomeActivity extends BaseActivity implements WelcomeContract.View {

  @Inject WelcomeContract.Presenter presenter;

  @Bind(R.id.welcome_cards) TextView welcomeCards;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getActivityComponent().inject(this);
    presenter.start(this);
  }

  @Override protected int getLayoutRes() {
    return R.layout.activity_welcome;
  }

  @Override public void showCards(List<Card> cards) {
    String welcomeText = "";
    for (Card card : cards) {
      welcomeText += card.getName() + "\n";
    }
    welcomeCards.setText(welcomeText);
  }
}
