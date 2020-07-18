package io.github.edmaputra.ed.edbase.model;


import io.github.edmaputra.ed.edbase.constant.DbColumn;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * The abstract of Address object
 *
 * @param <ID> type of Id
 * @author edmaputra
 * @since 0.0.1
 */
@MappedSuperclass
public abstract class BaseAddress<ID extends Serializable> extends BaseIdEntity<ID> {

  @NotBlank(message = "Should not Blank")
  @Size(max = DbColumn.NAME_LENGTH, min = 1, message = "Length should be 1 - " + DbColumn.NAME_LENGTH)
  @Column(length = DbColumn.STREET_LENGTH, nullable = false)
  protected String street;

  @NotBlank(message = "Should not Blank")
  @Size(max = DbColumn.CITY_NAME_LENGTH, min = 1, message = "Length should be 1 - " + DbColumn.CITY_NAME_LENGTH)
  @Column(length = DbColumn.CITY_NAME_LENGTH, nullable = false)
  protected String city;

  @NotBlank(message = "Should not Blank")
  @Size(max = DbColumn.PROVINCE_NAME_LENGTH, min = 1, message = "Length should be 1 - " + DbColumn.PROVINCE_NAME_LENGTH)
  @Column(length = DbColumn.PROVINCE_NAME_LENGTH, nullable = false)
  protected String province;

  @Column(length = DbColumn.ZIP_CODE_LENGTH)
  protected String zipCode;

  @Column(length = DbColumn.COUNTRY_LENGTH)
  protected String country;

//  @ManyToMany(mappedBy = "addresses")
//  private Set<P> persons = new HashSet<>();

  public BaseAddress() {
    super();
  }

  public BaseAddress(String street,
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

//  public Set<P> getPersons() {
//    return persons;
//  }
}
