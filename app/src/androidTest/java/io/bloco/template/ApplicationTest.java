package io.bloco.template;

import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.bloco.template.helpers.ApplicationHelper.getApplication;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ApplicationTest {

  @Test public void mode() throws Exception {
    assertThat(getApplication().getMode(), is(equalTo(AndroidApplication.Mode.TEST)));
  }
}