package io.github.edmaputra.ed.edbase.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Base Entity with Id
 *
 * @param <ID> type of Id
 * @author edmaputra
 * @since 0.0.1
 */
@MappedSuperclass
public abstract class BaseIdEntity<ID extends Serializable> extends BaseEntity {

  @Id
  @GeneratedValue
  protected ID id;

  public BaseIdEntity() {
    super();
  }

  public ID getId() {
    return id;
  }

  public void setId(ID id) {
    this.id = id;
  }
}
