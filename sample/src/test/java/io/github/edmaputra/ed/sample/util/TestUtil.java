package io.github.edmaputra.ed.sample.util;

import java.util.Objects;

public class TestUtil {

  public static boolean notNull(Object object) {
    if (object instanceof String) {
      String value = (String) object;
      return !value.equals("null");
    }
    return Objects.nonNull(object);
  }

}
