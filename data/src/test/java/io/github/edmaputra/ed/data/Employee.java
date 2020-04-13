package io.github.edmaputra.ed.data;

import io.github.edmaputra.ed.core.model.Gender;
import io.github.edmaputra.ed.core.model.MaritalStatus;
import io.github.edmaputra.ed.core.model.Person;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Employee extends Person<Long> {

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
}
