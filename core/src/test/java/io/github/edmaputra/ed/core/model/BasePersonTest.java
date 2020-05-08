package io.github.edmaputra.ed.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class BasePersonTest {

  private BasePerson basePerson;

  @BeforeEach
  void init() {
    basePerson = new BasePersonImpl();
  }

  @Test
  void givenPerson_whenInit_thenShouldReturnCorrectValue() {
    assertThat(basePerson.getFirstName()).isEqualTo("");
    assertThat(basePerson.getMiddleName()).isEqualTo("");
    assertThat(basePerson.getLastName()).isEqualTo("");
    assertThat(basePerson.getGender()).isEqualTo(Gender.UNKNOWN);
    assertThat(basePerson.getMaritalStatus()).isEqualTo(MaritalStatus.SINGLE);
    assertThat(basePerson.getBirthPlace()).isEqualTo("");
    assertThat(basePerson.getBirthDate()).isEqualTo(LocalDate.of(1900, Month.JANUARY, 1));
    assertThat(basePerson.getPhoneNumber()).isEqualTo("0000");
    assertThat(basePerson.getEmail()).isEqualTo("");
  }

  @Test
  void givenPerson_whenSetWithSetter_thenShouldReturnCorrectValue() {
    basePerson.setFirstName("Diana");
    basePerson.setMiddleName("Hakim");
    basePerson.setLastName("Utomo");
    basePerson.setGender(Gender.FEMALE);
    basePerson.setMaritalStatus(MaritalStatus.WIDOWER);
    basePerson.setBirthPlace("Bandung");
    basePerson.setBirthDate(LocalDate.of(2000, Month.MAY, 10));
    basePerson.setPhoneNumber("085012345678");
    basePerson.setEmail("diana.hakim@mail.com");

    assertThat(basePerson.getFirstName()).isEqualTo("Diana");
    assertThat(basePerson.getMiddleName()).isEqualTo("Hakim");
    assertThat(basePerson.getLastName()).isEqualTo("Utomo");
    assertThat(basePerson.getGender()).isEqualTo(Gender.FEMALE);
    assertThat(basePerson.getMaritalStatus()).isEqualTo(MaritalStatus.WIDOWER);
    assertThat(basePerson.getBirthPlace()).isEqualTo("Bandung");
    assertThat(basePerson.getBirthDate()).isEqualTo(LocalDate.of(2000, Month.MAY, 10));
    assertThat(basePerson.getPhoneNumber()).isEqualTo("085012345678");
    assertThat(basePerson.getEmail()).isEqualTo("diana.hakim@mail.com");
  }

  @Test
  void givenPerson_whenInitWithInjectedValue_thenShouldReturnCorrectValue() {
    basePerson = new BasePersonImpl(
        "Bangun", "Edma", "Saputra", Gender.MALE, MaritalStatus.MARRIED, "Kota Bangun",
        LocalDate.of(1990, Month.JULY, 8), "085112345678", "bangun.edma.saputra@gmail.com"
    );

    assertThat(basePerson.getFirstName()).isEqualTo("Bangun");
    assertThat(basePerson.getMiddleName()).isEqualTo("Edma");
    assertThat(basePerson.getLastName()).isEqualTo("Saputra");
    assertThat(basePerson.getGender()).isEqualTo(Gender.MALE);
    assertThat(basePerson.getMaritalStatus()).isEqualTo(MaritalStatus.MARRIED);
    assertThat(basePerson.getBirthPlace()).isEqualTo("Kota Bangun");
    assertThat(basePerson.getBirthDate()).isEqualTo(LocalDate.of(1990, Month.JULY, 8));
    assertThat(basePerson.getPhoneNumber()).isEqualTo("085112345678");
    assertThat(basePerson.getEmail()).isEqualTo("bangun.edma.saputra@gmail.com");
  }

  private static class BasePersonImpl extends BasePerson<String> {

    public BasePersonImpl() {
    }

    public BasePersonImpl(String firstName, String middleName, String lastName, Gender gender,
                          MaritalStatus maritalStatus, String birthPlace, LocalDate birthDate,
                          String phoneNumber, String email) {
      super(firstName, middleName, lastName, gender, maritalStatus, birthPlace, birthDate, phoneNumber, email);
    }
  }

}
