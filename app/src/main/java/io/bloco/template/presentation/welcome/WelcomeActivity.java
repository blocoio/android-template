package io.bloco.template.presentation.welcome;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.bloco.template.R;
import io.bloco.template.common.di.ActivityComponent;
import io.bloco.template.common.di.DaggerActivityComponent;
import io.bloco.template.data.models.Card;
import io.bloco.template.presentation.BaseActivity;
import java.util.List;
import javax.inject.Inject;

public class WelcomeActivity extends BaseActivity implements WelcomeContract.View {

  @Inject WelcomeContract.Presenter presenter;

  @Bind(R.id.welcome_cards) TextView welcomeCards;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);

    initializeInjectors();

    setupToolbar();

    presenter.start(this);
  }

  private void initializeInjectors() {
    ActivityComponent component = DaggerActivityComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
    component.inject(this);

    ButterKnife.bind(this);
  }

  @Override public void showCards(List<Card> cards) {
    String welcomeText = "";
    for (Card card : cards) {
      welcomeText += card.getName() + "\n";
    }
    welcomeCards.setText(welcomeText);
  }
}
