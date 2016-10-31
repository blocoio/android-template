package io.bloco.template.presentation.welcome;

import io.bloco.template.data.models.Card;
import io.bloco.template.domain.GetAllCards;
import io.bloco.template.testing.factories.CardFactory;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class WelcomePresenterTest {

  private WelcomePresenter welcomePresenter;
  private CardFactory cardFactory;

  @Mock private GetAllCards getAllCards;
  @Mock private WelcomePresenter.View view;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    cardFactory = new CardFactory();
    welcomePresenter = new WelcomePresenter(getAllCards);
    welcomePresenter.start(view);
  }

  @Test public void start() throws Exception {
    verify(getAllCards).get(eq(welcomePresenter));
  }

  @Test public void onGetAllCardsShowCards() throws Exception {
    List<Card> cards = cardFactory.buildList();
    welcomePresenter.onGetAllCards(cards);
    verify(view).showCards(eq(cards));
  }

  @Test public void onGetAllCardsDoesNotShowCardsIfEmpty() throws Exception {
    welcomePresenter.onGetAllCards(Collections.<Card>emptyList());
    verify(view, never()).showCards(any(List.class));
  }
}
