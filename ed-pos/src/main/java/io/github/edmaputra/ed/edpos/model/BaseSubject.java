package io.github.edmaputra.ed.edpos.model;

import io.github.edmaputra.ed.edbase.model.BaseAddress;
import io.github.edmaputra.ed.edbase.model.BaseIdAndNameEntity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseSubject<ID extends Serializable, A extends BaseAddress> extends BaseIdAndNameEntity<ID> {

  private A address;

  private String phone;

  private String email;

  public A getAddress() {
    return address;
  }

  public void setAddress(A address) {
    this.address = address;
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
