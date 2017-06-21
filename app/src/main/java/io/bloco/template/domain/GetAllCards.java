package io.bloco.template.domain;

import io.bloco.template.data.CardRepository;
import io.bloco.template.data.models.Card;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

public class GetAllCards {

  private final CardRepository cardRepository;

  @Inject public GetAllCards(CardRepository cardRepository) {
    this.cardRepository = cardRepository;
  }

  public Observable<List<Card>> get() {
    return cardRepository.getAll();
  }
}
