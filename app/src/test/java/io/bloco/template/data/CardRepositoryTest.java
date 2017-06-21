package io.bloco.template.data;

import io.bloco.template.data.models.Card;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CardRepositoryTest {

  private CardRepository cardRepository;

  @Before
  public void setUp() throws Exception {
    cardRepository = new CardRepository();
  }

  @Test
  public void getAll() throws Exception {
    List<Card> cards = cardRepository.getAll().blockingFirst();
    assertThat(cards.size(), is(equalTo(1)));
  }
}
