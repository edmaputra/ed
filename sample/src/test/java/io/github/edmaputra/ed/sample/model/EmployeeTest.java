package io.github.edmaputra.ed.sample.model;

import io.github.edmaputra.ed.edbase.model.Gender;
import io.github.edmaputra.ed.edbase.model.MaritalStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeTest {

  private Employee employee;

  @BeforeEach
  void init() {
    employee = new Employee();
  }

  @Test
  void givenPerson_whenInit_thenShouldReturnCorrectValue() {
    assertThat(employee.getFirstName()).isEqualTo("");
    assertThat(employee.getMiddleName()).isEqualTo("");
    assertThat(employee.getLastName()).isEqualTo("");
    assertThat(employee.getGender()).isEqualTo(Gender.UNKNOWN);
    assertThat(employee.getMaritalStatus()).isEqualTo(MaritalStatus.SINGLE);
    assertThat(employee.getBirthPlace()).isEqualTo("");
    assertThat(employee.getBirthDate()).isEqualTo(LocalDate.of(1900, Month.JANUARY, 1));
    assertThat(employee.getPhoneNumber()).isEqualTo("0000");
    assertThat(employee.getEmail()).isEqualTo("");
  }

  @Test
  void givenPerson_whenSetWithSetter_thenShouldReturnCorrectValue() {
    employee.setFirstName("Diana");
    employee.setMiddleName("Hakim");
    employee.setLastName("Utomo");
    employee.setGender(Gender.FEMALE);
    employee.setMaritalStatus(MaritalStatus.WIDOWER);
    employee.setBirthPlace("Bandung");
    employee.setBirthDate(LocalDate.of(2000, Month.MAY, 10));
    employee.setPhoneNumber("085012345678");
    employee.setEmail("diana.hakim@mail.com");

    assertThat(employee.getFirstName()).isEqualTo("Diana");
    assertThat(employee.getMiddleName()).isEqualTo("Hakim");
    assertThat(employee.getLastName()).isEqualTo("Utomo");
    assertThat(employee.getGender()).isEqualTo(Gender.FEMALE);
    assertThat(employee.getMaritalStatus()).isEqualTo(MaritalStatus.WIDOWER);
    assertThat(employee.getBirthPlace()).isEqualTo("Bandung");
    assertThat(employee.getBirthDate()).isEqualTo(LocalDate.of(2000, Month.MAY, 10));
    assertThat(employee.getPhoneNumber()).isEqualTo("085012345678");
    assertThat(employee.getEmail()).isEqualTo("diana.hakim@mail.com");
  }

  @Test
  void givenPerson_whenInitWithInjectedValue_thenShouldReturnCorrectValue() {
    employee = new Employee(
        "Bangun", "Edma", "Saputra", Gender.MALE, MaritalStatus.MARRIED, "Kota Bangun",
        LocalDate.of(1990, Month.JULY, 8), "085112345678", "bangun.edma.saputra@gmail.com"
    );

    assertThat(employee.getFirstName()).isEqualTo("Bangun");
    assertThat(employee.getMiddleName()).isEqualTo("Edma");
    assertThat(employee.getLastName()).isEqualTo("Saputra");
    assertThat(employee.getGender()).isEqualTo(Gender.MALE);
    assertThat(employee.getMaritalStatus()).isEqualTo(MaritalStatus.MARRIED);
    assertThat(employee.getBirthPlace()).isEqualTo("Kota Bangun");
    assertThat(employee.getBirthDate()).isEqualTo(LocalDate.of(1990, Month.JULY, 8));
    assertThat(employee.getPhoneNumber()).isEqualTo("085112345678");
    assertThat(employee.getEmail()).isEqualTo("bangun.edma.saputra@gmail.com");
  }

}
