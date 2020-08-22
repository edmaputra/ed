package io.github.edmaputra.ed.edpos.model;

import io.github.edmaputra.ed.edbase.model.BaseIdAndNameEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseItem<T extends BaseCategory<?>, ID extends Serializable> extends BaseIdAndNameEntity<ID> {

  @ManyToOne(optional = false)
  @JoinColumn(name = "category_id")
  private T category;

  public T getCategory() {
    return category;
  }

  public void setCategory(T category) {
    this.category = category;
  }
}
