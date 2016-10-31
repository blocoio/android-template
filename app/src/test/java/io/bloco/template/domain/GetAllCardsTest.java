package io.bloco.template.domain;

import io.bloco.template.data.Database;
import io.bloco.template.data.models.Card;
import io.bloco.template.testing.TestSchedulers;
import io.bloco.template.testing.factories.CardFactory;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class GetAllCardsTest {

  private GetAllCards getAllCards;
  private CardFactory cardFactory;

  @Mock private Database database;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    getAllCards = new GetAllCards(new TestSchedulers(), database);
    cardFactory = new CardFactory();
  }

  @Test public void get() throws Exception {
    List<Card> cards = cardFactory.buildList();
    when(database.getAllCards()).thenReturn(Observable.just(cards));
    List<Card> returnedCards = getAllCards.get().toBlocking().first();
    assertThat(returnedCards, is(equalTo(cards)));
  }
}