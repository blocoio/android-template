package io.bloco.template.presentation.welcome;

import io.bloco.template.common.di.PerActivity;
import io.bloco.template.data.models.Card;
import io.bloco.template.domain.GetAllCards;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;
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

    subscriptions.add(getAllCards.get().subscribe(new CardsSubscriber()));
  }

  public void stop() {
    subscriptions.unsubscribe();
  }

  private class CardsSubscriber extends Subscriber<List<Card>> {
    @Override public void onNext(List<Card> cards) {
      if (!cards.isEmpty()) {
        view.showCards(cards);
      }
    }

    @Override public void onError(Throwable e) {
    }

    @Override public void onCompleted() {
    }
  }

  public interface View {
    void showCards(List<Card> cards);
  }
}
