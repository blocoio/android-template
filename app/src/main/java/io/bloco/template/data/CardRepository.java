package io.bloco.template.data;

import io.bloco.template.common.di.PerApplication;
import io.bloco.template.data.models.Card;
import io.reactivex.Observable;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

@PerApplication
public class CardRepository {

  @Inject public CardRepository() {
  }

  public Observable<List<Card>> getAll() {
    return Observable.fromCallable(() -> {
      Card card = new Card();
      card.setName("John Smith");
      return Collections.singletonList(card);
    });
  }
}
