package io.bloco.template.presentation.welcome;

import io.bloco.template.common.di.PerActivity;
import io.bloco.template.data.models.Card;
import io.bloco.template.domain.GetAllCards;
import java.util.List;
import javax.inject.Inject;

@PerActivity public class WelcomePresenter implements GetAllCards.Callback {

  private final GetAllCards getAllCards;
  private View view;

  @Inject public WelcomePresenter(GetAllCards getAllCards) {
    this.getAllCards = getAllCards;
  }

  public void start(View view) {
    this.view = view;
    getAllCards.get(this);
  }

  @Override public void onGetAllCards(List<Card> cards) {
    if (!cards.isEmpty()) {
      view.showCards(cards);
    }
  }

  public interface View {
    void showCards(List<Card> cards);
  }
}
