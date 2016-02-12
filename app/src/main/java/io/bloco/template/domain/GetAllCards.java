package io.bloco.template.domain;

import io.bloco.template.data.Database;
import io.bloco.template.data.models.Card;
import java.util.List;
import javax.inject.Inject;

public class GetAllCards {

  private final Database database;

  public interface Callback {
    void onGetAllCards(List<Card> cards);
  }

  @Inject public GetAllCards(Database database) {
    this.database = database;
  }

  public void get(Callback callback) {
    List<Card> cards = this.database.getAllCards();
    callback.onGetAllCards(cards);
  }
}
