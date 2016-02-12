package io.bloco.template.domain;

import io.bloco.template.data.Database;
import io.bloco.template.data.models.Card;
import io.bloco.template.helpers.factories.CardFactory;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllCardsTest {

  private GetAllCards getAllCards;
  private CardFactory cardFactory;

  @Mock private Database database;
  @Mock private GetAllCards.Callback callback;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    getAllCards = new GetAllCards(database);
    cardFactory = new CardFactory();
  }

  @Test public void get() throws Exception {
    List<Card> cards = cardFactory.buildList();
    when(database.getAllCards()).thenReturn(cards);
    getAllCards.get(callback);
    verify(callback).onGetAllCards(eq(cards));
  }
}