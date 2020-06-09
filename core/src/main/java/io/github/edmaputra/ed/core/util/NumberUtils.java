package io.github.edmaputra.ed.core.util;

public class NumberUtils {

  public static boolean isNumber(String s) {
    try {
      Long.parseLong(s);
      Integer.parseInt(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isFloatingNumber(String s) {
    try {
      Double.parseDouble(s);
      Float.parseFloat(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
