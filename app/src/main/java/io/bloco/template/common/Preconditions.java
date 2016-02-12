package io.bloco.template.common;

import android.support.annotation.Nullable;

// Based on http://google-collections.googlecode.com/svn-history/r78/trunk/javadoc/com/google/common/base/Preconditions.html

public class Preconditions {

  public static void checkArgument(boolean expression, @Nullable Object errorMessage) {
    if (!expression) {
      throw new IllegalArgumentException(String.valueOf(errorMessage));
    }
  }

  public static void checkState(boolean expression, @Nullable Object errorMessage) {
    if (!expression) {
      throw new IllegalStateException(String.valueOf(errorMessage));
    }
  }

  public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
    if (reference == null) {
      throw new NullPointerException(String.valueOf(errorMessage));
    }
    return reference;
  }

  public static <T> T checkNull(T reference, @Nullable Object errorMessage) {
    checkState(reference == null, errorMessage);
    return reference;
  }
}