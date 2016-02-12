package io.bloco.template.presentation.welcome;

import io.bloco.template.data.models.Card;
import java.util.List;

public class WelcomeContract {
  public interface View {
    void showCards(List<Card> cards);
  }

  public interface Presenter {
    void start(View view);
  }
}
