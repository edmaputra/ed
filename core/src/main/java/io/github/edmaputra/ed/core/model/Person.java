package io.github.edmaputra.ed.core.model;

import io.github.edmaputra.ed.core.constant.DbColumn;

import javax.persistence.Column;
import java.time.LocalDate;

public abstract class Person<T> extends BaseIdEntity<T> {

  @Column(length = DbColumn.NAME_LENGTH, nullable = false)
  protected String firstName;

  @Column(length = DbColumn.NAME_LENGTH)
  protected String middleName;

  @Column(length = DbColumn.NAME_LENGTH, nullable = false)
  protected String lastName;

  @Column(length = DbColumn.GENDER_LENGTH, nullable = false)
  protected Gender gender;

  @Column(length = DbColumn.MARITAL_STATUS_LENGTH, nullable = false)
  protected MaritalStatus maritalStatus;

  @Column(length = DbColumn.CITY_NAME_LENGTH, nullable = false)
  protected String birthPlace;

  @Column(nullable = false)
  protected LocalDate birthDate;

  @Column(length = DbColumn.PHONE_NUMBER_LENGTH, nullable = false)
  protected String phoneNumber;

  @Column(length = DbColumn.EMAIL_LENGTH)
  protected String email;

  public Person() {
    this("", "", "", Gender.UNKNOWN, MaritalStatus.SINGLE, "",
        LocalDate.of(1900, 1, 1), "0000", "");
  }

  public Person(String firstName,
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
