package io.github.edmaputra.ed.sample.model;

import io.github.edmaputra.ed.core.annotation.Filterable;
import io.github.edmaputra.ed.core.model.BasePerson;
import io.github.edmaputra.ed.core.model.Gender;
import io.github.edmaputra.ed.core.model.MaritalStatus;
import org.hibernate.annotations.Filter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Employee extends BasePerson<Long> {

  public Employee() {
    super();
  }

  public Employee(String firstName,
                  String middleName,
                  String lastName,
                  Gender gender,
                  MaritalStatus maritalStatus,
                  String birthPlace,
                  LocalDate birthDate,
                  String phoneNumber,
                  String email) {
    super(firstName, middleName, lastName, gender, maritalStatus, birthPlace, birthDate, phoneNumber, email);
  }

  public Employee(String firstName,
                  String middleName,
                  String lastName,
                  Gender gender,
                  MaritalStatus maritalStatus,
                  String birthPlace,
                  LocalDate birthDate,
                  String phoneNumber,
                  String email,
                  int age) {
    super(firstName, middleName, lastName, gender, maritalStatus, birthPlace, birthDate, phoneNumber, email);
    this.age = age;
  }

  @Filterable
  private int age;

  public int getAge() {
    return age;
  }
}
