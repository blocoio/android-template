package io.bloco.template.helpers.factories;

import io.bloco.faker.Faker;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseFactory<T> {

  private static final int DEFAULT_LIST_SIZE = 3;

  protected Faker faker;

  public BaseFactory() {
    faker = new Faker();
  }

  public abstract T build();

  public List<T> buildList() {
    return buildList(DEFAULT_LIST_SIZE);
  }

  public List<T> buildList(int size) {
    List<T> list = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      list.add(build());
    }
    return list;
  }
}
