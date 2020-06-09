package io.github.edmaputra.ed.core.annotation;

public class FilterModel {

  private String field;

  private Class<?> type;

  public FilterModel(String field, Class<?> type) {
    this.field = field;
    this.type = type;
  }

  public String getField() {
    return field;
  }

  public Class<?> getType() {
    return type;
  }

}
