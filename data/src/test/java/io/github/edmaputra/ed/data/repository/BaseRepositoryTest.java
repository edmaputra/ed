package io.github.edmaputra.ed.data.repository;

import io.github.edmaputra.ed.data.DataInit;
import io.github.edmaputra.ed.data.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Pageable;

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
    assertListSizeWithRecordedIsTrue(3);

    setRecorded(false, e1);
    assertListSizeWithRecordedIsTrue(2);

    setRecorded(false, e2);
    assertListSizeWithRecordedIsTrue(1);

    setRecorded(false, e3);
    assertListSizeWithRecordedIsTrue(0);

    setRecorded(true, e1, e2, e3);
    assertListSizeWithRecordedIsTrue(3);
  }

  @Test
  void givenEmployeeList_whenFindByRecorded_thenShouldReturnCorrectSize() {
    assertListSizeWithRecorded(true, 3);
    assertListSizeWithRecorded(false, 0);

    setRecorded(false, e1);
    assertListSizeWithRecorded(true, 2);
    assertListSizeWithRecorded(false, 1);

    setRecorded(false, e2);
    assertListSizeWithRecorded(true, 1);
    assertListSizeWithRecorded(false, 2);

    setRecorded(false, e3);
    assertListSizeWithRecorded(true, 0);
    assertListSizeWithRecorded(false, 3);

    setRecorded(true, e1, e2, e3);
    assertListSizeWithRecorded(true, 3);
    assertListSizeWithRecorded(false, 0);

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

  private void assertListSizeWithRecordedIsTrue(int expectedValue) {
    int size = repository.findByRecordedTrue(Pageable.unpaged()).get().getSize();
    assertThat(size).isEqualTo(expectedValue);
  }

  private void assertListSizeWithRecorded(boolean isRecorded, int expectedValue) {
    int size = repository.findByRecorded(isRecorded, Pageable.unpaged()).get().getSize();
    assertThat(size).isEqualTo(expectedValue);
  }

}