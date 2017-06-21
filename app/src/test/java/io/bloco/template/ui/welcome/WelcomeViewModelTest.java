package io.bloco.template.ui.welcome;

import io.bloco.template.data.models.Card;
import io.bloco.template.domain.GetAllCards;
import io.bloco.template.testing.factories.CardFactory;
import io.reactivex.Observable;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WelcomeViewModelTest {

  @Mock private GetAllCards getAllCards;
  @InjectMocks private WelcomeViewModel viewModel;

  private CardFactory cardFactory = new CardFactory();

  @Test
  public void onGetAllCardsShowCards() throws Exception {
    List<Card> cards = cardFactory.buildList();
    when(getAllCards.get()).thenReturn(Observable.just(cards));

    viewModel.onCreate();

    assertThat(viewModel.getCards().blockingFirst()).isEqualTo(cards);
  }

  @Test
  public void onGetAllCardsDoesNotShowCardsIfEmpty() throws Exception {
    when(getAllCards.get()).thenReturn(Observable.just(Collections.emptyList()));

    viewModel.onCreate();

    assertThat(viewModel.getCards().blockingFirst()).isEmpty();
  }
}
