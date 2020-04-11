package io.github.edmaputra.ed.core.model;

import io.github.edmaputra.ed.core.constant.DbColumn;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Address<T> extends BaseIdEntity<T> {

  @Column(length = DbColumn.STREET_LENGTH)
  protected String street;

  @Column(length = DbColumn.CITY_NAME_LENGTH)
  protected String city;

  @Column(length = DbColumn.PROVINCE_NAME_LENGTH)
  protected String province;

  @Column(length = DbColumn.ZIP_CODE_LENGTH)
  protected String zipCode;

  @Column(length = DbColumn.COUNTRY_LENGTH)
  protected String country;

  public Address() {
    super();
  }

  public Address(String street,
                 String city,
                 String province,
                 String zipCode,
                 String country) {
    super();
    this.street = street;
    this.city = city;
    this.province = province;
    this.zipCode = zipCode;
    this.country = country;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
