package io.bloco.template.ui.welcome;

import android.os.Bundle;
import butterknife.BindView;
import io.bloco.template.R;
import io.bloco.template.common.di.viewmodel.ActivityViewModelProvider;
import io.bloco.template.data.models.Card;
import io.bloco.template.ui.BaseActivity;
import java.util.List;
import javax.inject.Inject;

public class WelcomeActivity extends BaseActivity {

  @BindView(R.id.welcome_cards) WelcomeView welcomeView;

  @Inject ActivityViewModelProvider activityViewModelProvider;

  private WelcomeViewModel viewModel;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getActivityComponent().inject(this);
    viewModel = activityViewModelProvider.get(WelcomeViewModel.class);
    viewModel.onCreate();

    viewModel.getCards()
        .compose(rxSchedulers.apply())
        .compose(provider.bindToLifecycle())
        .subscribe(this::showCards);
  }

  @Override protected int getLayoutRes() {
    return R.layout.activity_welcome;
  }

  private void showCards(List<Card> cards) {
    String welcomeText = "";
    for (Card card : cards) {
      welcomeText += card.getName() + "\n";
    }
    welcomeView.setText(welcomeText);
  }
}
