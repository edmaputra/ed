package io.github.edmaputra.ed.core.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseIdEntity<T> extends BaseEntity {

  @Id
  @GeneratedValue
  protected T id;

  public BaseIdEntity() {
    super();
  }

  public BaseIdEntity(String version, Instant createTime, String creator, Instant updateTime, String updater, boolean recorded) {
    super(version, createTime, creator, updateTime, updater, recorded);
  }

  public T getId() {
    return id;
  }

  public void setId(T id) {
    this.id = id;
  }
}
