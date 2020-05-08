package io.github.edmaputra.ed.core.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base Entity with Id
 *
 * @author edmaputra
 * @since 0.0.1
 * @param <T> type of Id
 */
@MappedSuperclass
public abstract class BaseIdEntity<T> extends BaseEntity {

  @Id
  @GeneratedValue
  protected T id;

  public BaseIdEntity() {
    super();
  }

  public T getId() {
    return id;
  }

  public void setId(T id) {
    this.id = id;
  }
}
