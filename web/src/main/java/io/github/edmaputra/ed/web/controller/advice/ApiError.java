package io.github.edmaputra.ed.web.controller.advice;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError {

  private HttpStatus status;

  private String message;

  private List<String> errors;

  private int statusCode;

  private String field;

  public ApiError(){}

  public ApiError(HttpStatus status, String message, List<String> errors) {
    this.status = status;
    this.message = message;
    this.errors = errors;
    this.statusCode = status.value();
  }

  public ApiError(HttpStatus status, String message, String error) {
    this.status = status;
    this.message = message;
    errors = Arrays.asList(error);
    this.statusCode = status.value();
  }

  public ApiError(HttpStatus status, String message, List<String> errors, String field) {
    this(status, message, errors);
    this.field = field;
  }

  public ApiError(HttpStatus status, String message, String error, String field) {
    this(status, message, error);
    this.field = field;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public List<String> getErrors() {
    return errors;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getField() {
    return field;
  }

}
