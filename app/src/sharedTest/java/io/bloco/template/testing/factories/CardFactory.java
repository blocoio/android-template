package io.bloco.template.helpers.factories;

import io.bloco.template.data.models.Card;

public class CardFactory extends BaseFactory<Card> {

  @Override public Card build() {
    Card card = new Card();
    card.setName(faker.name.name());
    return card;
  }
}
