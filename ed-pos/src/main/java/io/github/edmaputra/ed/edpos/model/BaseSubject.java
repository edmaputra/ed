package io.github.edmaputra.ed.edpos.model;

import io.github.edmaputra.ed.edbase.model.BaseAddress;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseSubject<ID extends Serializable> extends BaseAddress<ID> {

  private String name;

  private String phone;

  private String email;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
