package io.github.edmaputra.ed.edbase.model.error;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ErrorModel {

  private HttpStatus status;

  private int statusCode;

  private Map<String, List<String>> errors;


  public ErrorModel() {
    errors = new HashMap<>();
  }

  public ErrorModel(HttpStatus status, Map<String, List<String>> errors) {
    this.status = status;
    this.errors = errors;
    this.statusCode = status.value();
  }

  public void addErrors(String key, String value) {
    List<String> values = errors.get(key);
    if (Objects.isNull(values)) {
      values = new ArrayList<>();
    }
    values.add(value);
    errors.put(key, values);
  }

  public HttpStatus getStatus() {
    return status;
  }

  public Map<String, List<String>> getErrors() {
    return errors;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
    this.statusCode = status.value();
  }

  public void setErrors(Map<String, List<String>> errors) {
    this.errors = errors;
  }
}
