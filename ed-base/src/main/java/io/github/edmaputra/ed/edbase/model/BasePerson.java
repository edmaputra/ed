package io.github.edmaputra.ed.edbase.model;


import io.github.edmaputra.ed.edbase.annotation.Filterable;
import io.github.edmaputra.ed.edbase.constant.DbColumn;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * The abstract of Person object
 *
 * @param <ID> type of ID
 * @author edmaputra
 * @since 0.0.1
 */
@MappedSuperclass
public abstract class BasePerson<ID extends Serializable> extends BaseIdEntity<ID> {


  @NotBlank
  @Size(max = DbColumn.NAME_LENGTH, min = 2, message = "Length should be between 2 - " + DbColumn.NAME_LENGTH)
  @Column(length = DbColumn.NAME_LENGTH, nullable = false)
  @Filterable
  protected String firstName;

  @Column(length = DbColumn.NAME_LENGTH)
  @Filterable
  protected String middleName;


  @NotBlank
  @Size(max = DbColumn.NAME_LENGTH, min = 2, message = "Length should be between 2 - " + DbColumn.NAME_LENGTH)
  @Column(length = DbColumn.NAME_LENGTH, nullable = false)
  @Filterable
  protected String lastName;

  @NotNull(message = "Should not null")
  @Column(length = DbColumn.GENDER_LENGTH, nullable = false)
  @Filterable
  protected Gender gender;

  @NotNull(message = "Should not null")
  @Column(length = DbColumn.MARITAL_STATUS_LENGTH, nullable = false)
  @Filterable
  protected MaritalStatus maritalStatus;

  @NotBlank(message = "Should not Blank")
  @Size(max = DbColumn.CITY_NAME_LENGTH, min = 1, message = "Length should be 1 - " + DbColumn.CITY_NAME_LENGTH)
  @Column(length = DbColumn.CITY_NAME_LENGTH, nullable = false)
  @Filterable
  protected String birthPlace;

  @NotNull
  @Column(nullable = false)
  protected LocalDate birthDate;

  @NotBlank()
  @Size(max = DbColumn.PHONE_NUMBER_LENGTH, min = 1, message = "Length should be 1 - " + DbColumn.PHONE_NUMBER_LENGTH)
  @Column(length = DbColumn.PHONE_NUMBER_LENGTH, nullable = false)
  @Filterable
  protected String phoneNumber;

  @Email
  @Column(length = DbColumn.EMAIL_LENGTH)
  @Filterable
  protected String email;

  public BasePerson() {
    this("", "", "", Gender.UNKNOWN, MaritalStatus.SINGLE, "",
        LocalDate.of(1900, 1, 1), "0000", "");
  }

  public BasePerson(String firstName,
                    String middleName,
                    String lastName,
                    Gender gender,
                    MaritalStatus maritalStatus,
                    String birthPlace,
                    LocalDate birthDate,
                    String phoneNumber,
                    String email) {
    super();
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.gender = gender;
    this.maritalStatus = maritalStatus;
    this.birthPlace = birthPlace;
    this.birthDate = birthDate;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public MaritalStatus getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  public String getBirthPlace() {
    return birthPlace;
  }

  public void setBirthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
