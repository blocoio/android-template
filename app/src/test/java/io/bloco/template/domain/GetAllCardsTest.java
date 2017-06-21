package io.bloco.template.domain;

import io.bloco.template.data.CardRepository;
import io.bloco.template.data.models.Card;
import io.bloco.template.testing.factories.CardFactory;
import io.reactivex.Observable;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetAllCardsTest {

  @Mock private CardRepository cardRepository;
  @InjectMocks private GetAllCards getAllCards;

  private CardFactory cardFactory = new CardFactory();

  @Test
  public void get() throws Exception {
    List<Card> cards = cardFactory.buildList();
    when(cardRepository.getAll()).thenReturn(Observable.just(cards));
    List<Card> returnedCards = getAllCards.get().blockingFirst();
    assertThat(returnedCards, is(equalTo(cards)));
  }
}