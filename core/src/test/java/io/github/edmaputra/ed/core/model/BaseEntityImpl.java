package io.github.edmaputra.ed.core.model;

import java.time.Instant;

public class BaseEntityImpl extends BaseEntity {

  public BaseEntityImpl() {
    super();
  }

  public BaseEntityImpl(String version, Instant createdOn, String creator, Instant updatedOn, String updater, boolean recorded) {
    super(version, createdOn, creator, updatedOn, updater, recorded);
  }
}
