package io.bloco.template.ui.welcome;

import io.bloco.template.data.models.Card;
import io.bloco.template.domain.GetAllCards;
import io.bloco.template.ui.BaseViewModel;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class WelcomeViewModel extends BaseViewModel {

  private final GetAllCards getAllCards;
  private final BehaviorSubject<List<Card>> cardsSubject;
  private boolean loaded;

  @Inject public WelcomeViewModel(GetAllCards getAllCards) {
    this.getAllCards = getAllCards;
    this.cardsSubject = BehaviorSubject.createDefault(Collections.emptyList());
  }

  void onCreate() {
    if (loaded) {
      return;
    }
    loaded = true;

    addDisposables(
        loadCards()
    );
  }

  Observable<List<Card>> getCards() {
    return cardsSubject.hide();
  }

  private Disposable loadCards() {
    return getAllCards.get()
        .subscribe(cardsSubject::onNext);
  }
}
