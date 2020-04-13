package io.github.edmaputra.ed.data.repository;

import io.github.edmaputra.ed.core.model.Gender;
import io.github.edmaputra.ed.core.model.MaritalStatus;
import io.github.edmaputra.ed.data.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BaseRepositoryTest {

  @Autowired
  TestEntityManager testEntityManager;

  @Autowired
  BaseRepositoryEmployee repository;

  private Employee e1, e2, e3;

  @BeforeEach
  public void init() {
    e1 = new Employee("Bangun",
        "Edma",
        "Saputra",
        Gender.MALE,
        MaritalStatus.MARRIED,
        "Kota Bangun",
        LocalDate.of(1990, Month.AUGUST, 1),
        "085112345678",
        "bangun.edma.saputra@gmail.com"
    );

    e2 = new Employee("Rina",
        "",
        "Wibowo",
        Gender.FEMALE,
        MaritalStatus.SINGLE,
        "Bandung",
        LocalDate.of(1991, Month.SEPTEMBER, 5),
        "08520987654",
        "rina.wibowo@mail.com"
    );

    e3 = new Employee("Alex",
        "Maningger",
        "Sanchez",
        Gender.MALE,
        MaritalStatus.DIVORCEE,
        "Espanyol",
        LocalDate.of(1988, Month.FEBRUARY, 16),
        "085555550000",
        "maningger@mail.com"
    );

    testEntityManager.persist(e1);
    testEntityManager.persist(e2);
    testEntityManager.persist(e3);
  }

  @Test
  void givenEmployeeList_whenFindByRecordedTrue_thenShouldReturnCorrectSize() {
    assertListSize(3);

    setRecorded(false, e1);
    assertListSize(2);

    setRecorded(false, e2);
    assertListSize(1);

    setRecorded(false, e3);
    assertListSize(0);

    setRecorded(true, e1, e2, e3);
    assertListSize(3);
  }

  @AfterEach
  void clear() {
    testEntityManager.remove(e1);
    testEntityManager.remove(e2);
    testEntityManager.remove(e3);
  }

  private void setRecorded(boolean record, Employee... e) {
    for (Employee employee : e) {
      employee.setRecorded(record);
      testEntityManager.merge(employee);
    }
  }

  private void assertListSize(int expectedValue) {
    int size = repository.findByRecordedTrue(Pageable.unpaged()).get().getSize();
    assertThat(size).isEqualTo(expectedValue);
  }

}
