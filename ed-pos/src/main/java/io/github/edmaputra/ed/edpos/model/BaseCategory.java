package io.github.edmaputra.ed.edpos.model;

import io.github.edmaputra.ed.edbase.model.BaseIdAndNameEntity;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@MappedSuperclass
public abstract class BaseCategory<ID extends Serializable, T extends BaseItem<?>> extends BaseIdAndNameEntity<ID> {

  @OneToMany
  @JoinColumn(name = "category_id")
  private Set<T> items;

  public Set<T> getItems() {
    return items;
  }

  public void setItems(Set<T> items) {
    this.items = items;
  }
}
