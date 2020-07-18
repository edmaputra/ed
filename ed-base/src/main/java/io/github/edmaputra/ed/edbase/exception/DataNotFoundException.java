package io.github.edmaputra.ed.edbase.exception;

/**
 * Data Empty Exception
 *
 * @author edmaputra
 * @since 0.0.1
 */
public class DataNotFoundException extends Exception {

  public DataNotFoundException() {
    super("Data Not Found");
  }

  public DataNotFoundException(String message) {
    super(message);
  }

}
