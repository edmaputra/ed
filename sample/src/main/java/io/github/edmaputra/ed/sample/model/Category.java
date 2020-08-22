package io.github.edmaputra.ed.sample.model;

import io.github.edmaputra.ed.edpos.model.BaseCategory;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Category extends BaseCategory<UUID> {

  public Category() {
  }

  public Category(String name) {
    this();
    this.setName(name);
  }
}
