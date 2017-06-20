package io.bloco.template.ui.welcome;

import io.bloco.template.common.di.PerActivity;
import io.bloco.template.data.models.Card;
import io.bloco.template.domain.GetAllCards;
import java.util.List;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;

@PerActivity public class WelcomePresenter {

  private final GetAllCards getAllCards;
  private final CompositeSubscription subscriptions;
  private View view;

  @Inject public WelcomePresenter(GetAllCards getAllCards) {
    this.getAllCards = getAllCards;
    this.subscriptions = new CompositeSubscription();
  }

  public void start(View view) {
    this.view = view;

    subscriptions.add(
        getAllCards.get().subscribe(view::showCards)
    );
  }

  public void stop() {
    subscriptions.unsubscribe();
  }

  public interface View {
    void showCards(List<Card> cards);
  }
}
