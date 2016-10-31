package io.bloco.template.data;

import io.bloco.template.data.models.Card;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class DatabaseTest {

  private Database database;

  @Before public void setUp() throws Exception {
    database = new Database();
  }

  @Test public void getAllCards() throws Exception {
    List<Card> cards = database.getAllCards().toBlocking().first();
    assertThat(cards.size(), is(equalTo(1)));
  }
}
