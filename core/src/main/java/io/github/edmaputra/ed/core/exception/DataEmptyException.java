package io.github.edmaputra.ed.core.exception;

/**
 * Data Empty Exception
 *
 * @author edmaputra
 * @since 0.0.1
 */
public class DataEmptyException extends Exception {

  public DataEmptyException() {
    super("Data is empty");
  }

  public DataEmptyException(String message) {
    super(message);
  }

}
