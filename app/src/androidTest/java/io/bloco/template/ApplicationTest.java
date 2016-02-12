package io.bloco.template;

import android.test.ApplicationTestCase;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ApplicationTest extends ApplicationTestCase<AndroidApplication> {
  public ApplicationTest() {
    super(AndroidApplication.class);
  }

  public void testMode() throws Exception {
    assertThat(getApplication().getMode(), is(equalTo(AndroidApplication.Mode.TEST)));
  }
}