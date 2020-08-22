package io.github.edmaputra.ed.sample.model;

import io.github.edmaputra.ed.edpos.model.BaseItem;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Item extends BaseItem<Category, UUID> {

  public Item() {
  }

  public Item(String name) {
    this();
    this.setName(name);
  }
}
