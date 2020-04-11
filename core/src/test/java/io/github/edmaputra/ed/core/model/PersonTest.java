package io.github.edmaputra.ed.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {

  private Person person;

  @BeforeEach
  void init() {
    person = new PersonImpl();
  }

  @Test
  void givenPerson_whenInit_thenShouldReturnCorrectValue() {
    assertThat(person.getFirstName()).isEqualTo("");
    assertThat(person.getMiddleName()).isEqualTo("");
    assertThat(person.getLastName()).isEqualTo("");
    assertThat(person.getGender()).isEqualTo(Gender.UNKNOWN);
    assertThat(person.getMaritalStatus()).isEqualTo(MaritalStatus.SINGLE);
    assertThat(person.getBirthPlace()).isEqualTo("");
    assertThat(person.getBirthDate()).isEqualTo(LocalDate.of(1900, Month.JANUARY, 1));
    assertThat(person.getPhoneNumber()).isEqualTo("0000");
    assertThat(person.getEmail()).isEqualTo("");
  }

  @Test
  void givenPerson_whenSetWithSetter_thenShouldReturnCorrectValue() {
    person.setFirstName("Diana");
    person.setMiddleName("Hakim");
    person.setLastName("Utomo");
    person.setGender(Gender.FEMALE);
    person.setMaritalStatus(MaritalStatus.WIDOWER);
    person.setBirthPlace("Bandung");
    person.setBirthDate(LocalDate.of(2000, Month.MAY, 10));
    person.setPhoneNumber("085012345678");
    person.setEmail("diana.hakim@mail.com");

    assertThat(person.getFirstName()).isEqualTo("Diana");
    assertThat(person.getMiddleName()).isEqualTo("Hakim");
    assertThat(person.getLastName()).isEqualTo("Utomo");
    assertThat(person.getGender()).isEqualTo(Gender.FEMALE);
    assertThat(person.getMaritalStatus()).isEqualTo(MaritalStatus.WIDOWER);
    assertThat(person.getBirthPlace()).isEqualTo("Bandung");
    assertThat(person.getBirthDate()).isEqualTo(LocalDate.of(2000, Month.MAY, 10));
    assertThat(person.getPhoneNumber()).isEqualTo("085012345678");
    assertThat(person.getEmail()).isEqualTo("diana.hakim@mail.com");
  }

  @Test
  void givenPerson_whenInitWithInjectedValue_thenShouldReturnCorrectValue() {
    person = new PersonImpl(
        "Bangun", "Edma", "Saputra", Gender.MALE, MaritalStatus.MARRIED, "Kota Bangun",
        LocalDate.of(1990, Month.JULY, 8), "085112345678", "bangun.edma.saputra@gmail.com"
    );

    assertThat(person.getFirstName()).isEqualTo("Bangun");
    assertThat(person.getMiddleName()).isEqualTo("Edma");
    assertThat(person.getLastName()).isEqualTo("Saputra");
    assertThat(person.getGender()).isEqualTo(Gender.MALE);
    assertThat(person.getMaritalStatus()).isEqualTo(MaritalStatus.MARRIED);
    assertThat(person.getBirthPlace()).isEqualTo("Kota Bangun");
    assertThat(person.getBirthDate()).isEqualTo(LocalDate.of(1990, Month.JULY, 8));
    assertThat(person.getPhoneNumber()).isEqualTo("085112345678");
    assertThat(person.getEmail()).isEqualTo("bangun.edma.saputra@gmail.com");
  }

  private static class PersonImpl extends Person<String> {

    public PersonImpl() {
    }

    public PersonImpl(String firstName, String middleName, String lastName, Gender gender,
                      MaritalStatus maritalStatus, String birthPlace, LocalDate birthDate,
                      String phoneNumber, String email) {
      super(firstName, middleName, lastName, gender, maritalStatus, birthPlace, birthDate, phoneNumber, email);
    }
  }

}
