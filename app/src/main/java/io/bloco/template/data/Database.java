package io.bloco.template.data;

import io.bloco.template.data.models.Card;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class Database {
  @Inject public Database() {
  }

  public Observable<List<Card>> getAllCards() {
    List<Card> cards = new ArrayList<>();
    Card card = new Card();
    card.setName("John Smith");
    cards.add(card);
    return Observable.just(cards);
  }
}
