package io.github.edmaputra.ed.sample.repository;

import io.github.edmaputra.ed.sample.DataInit;
import io.github.edmaputra.ed.sample.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

  @Autowired
  TestEntityManager testEntityManager;

  @Autowired
  private EmployeeRepository repository;

  private Employee e1, e2, e3;

  @BeforeEach
  public void init() {
    DataInit init = new DataInit();
    e1 = init.getEmployee(0);
    e2 = init.getEmployee(1);
    e3 = init.getEmployee(2);

    testEntityManager.persist(e1);
    testEntityManager.persist(e2);
    testEntityManager.persist(e3);
  }

  @Test
  void givenEmployeeList_whenFindByRecordedTrue_thenShouldReturnCorrectSize() {
    assertListSizeWithDeleteFlagIsFalse(3);

    setDeleteFlag(true, e1);
    assertListSizeWithDeleteFlagIsFalse(2);

    setDeleteFlag(true, e2);
    assertListSizeWithDeleteFlagIsFalse(1);

    setDeleteFlag(true, e3);
    assertListSizeWithDeleteFlagIsFalse(0);

    setDeleteFlag(true, e1, e2, e3);
    assertListSizeWithDeleteFlagIsFalse(0);
  }

  @Test
  void givenEmployeeList_whenFindByRecorded_thenShouldReturnCorrectSize() {
    assertListSizeWithDeleteFlag(true, 0);
    assertListSizeWithDeleteFlag(false, 3);

    setDeleteFlag(true, e1);
    assertListSizeWithDeleteFlag(true, 1);
    assertListSizeWithDeleteFlag(false, 2);

    setDeleteFlag(true, e2);
    assertListSizeWithDeleteFlag(true, 2);
    assertListSizeWithDeleteFlag(false, 1);

    setDeleteFlag(true, e3);
    assertListSizeWithDeleteFlag(true, 3);
    assertListSizeWithDeleteFlag(false, 0);

    setDeleteFlag(true, e1, e2, e3);
    assertListSizeWithDeleteFlag(true, 3);
    assertListSizeWithDeleteFlag(false, 0);

  }

  @AfterEach
  void clear() {
    testEntityManager.remove(e1);
    testEntityManager.remove(e2);
    testEntityManager.remove(e3);
    testEntityManager.flush();
  }

  private void setDeleteFlag(boolean record, Employee... e) {
    for (Employee employee : e) {
      employee.setDeleteFlag(record);
      testEntityManager.merge(employee);
    }
  }

  private void assertListSizeWithDeleteFlagIsFalse(int expectedValue) {
    Page<Employee> employees = repository.findByDeleteFlagFalse(Pageable.unpaged()).orElse(Page.empty());
    assertThat(expectedValue).isEqualTo(employees.getTotalElements());
  }

  private void assertListSizeWithDeleteFlag(boolean isRecorded, int expectedValue) {
    Page<Employee> employees = repository.findByDeleteFlag(isRecorded, Pageable.unpaged()).orElse(Page.empty());
    assertThat(expectedValue).isEqualTo(employees.getTotalElements());
  }

}
