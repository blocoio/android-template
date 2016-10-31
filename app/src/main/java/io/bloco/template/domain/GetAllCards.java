package io.bloco.template.domain;

import io.bloco.template.common.Schedulers;
import io.bloco.template.data.Database;
import io.bloco.template.data.models.Card;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class GetAllCards {

  private final Schedulers schedulers;
  private final Database database;

  @Inject public GetAllCards(Schedulers schedulers, Database database) {
    this.schedulers = schedulers;
    this.database = database;
  }

  public Observable<List<Card>> get() {
    return database.getAllCards().compose(schedulers.<List<Card>>apply());
  }
}
