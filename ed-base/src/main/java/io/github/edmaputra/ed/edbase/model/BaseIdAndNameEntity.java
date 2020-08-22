package io.github.edmaputra.ed.edbase.model;

import io.github.edmaputra.ed.edbase.annotation.Filterable;
import io.github.edmaputra.ed.edbase.constant.DbColumn;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Base Entity with Id and Name
 *
 * @param <ID> type of Id
 * @author edmaputra
 * @since 0.4.0
 */
@MappedSuperclass
public abstract class BaseIdAndNameEntity<ID extends Serializable> extends BaseIdEntity<ID> {

  @NotBlank
  @Size(min = 2, max = DbColumn.NAME_LENGTH, message = "Length should be between 2 - " + DbColumn.NAME_LENGTH + " character")
  @Filterable
  protected String name;

  public BaseIdAndNameEntity() {
    super();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
