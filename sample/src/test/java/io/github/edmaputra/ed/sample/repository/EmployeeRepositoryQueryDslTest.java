package io.github.edmaputra.ed.sample.repository;

import io.github.edmaputra.ed.edbase.predicate.BasePredicate;
import io.github.edmaputra.ed.sample.DataInit;
import io.github.edmaputra.ed.sample.model.Employee;
import io.github.edmaputra.ed.sample.predicate.EmployeePredicate;
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
public class EmployeeRepositoryQueryDslTest {

  @Autowired
  TestEntityManager testEntityManager;

  @Autowired
  EmployeeRepository employeeRepository;

  BasePredicate<Employee> employeePredicate;

  DataInit data;

  @BeforeEach
  void init() {
    data = new DataInit();
    employeePredicate = new EmployeePredicate();
    testEntityManager.persist(data.getEmployee(0));
    testEntityManager.persist(data.getEmployee(1));
    testEntityManager.persist(data.getEmployee(2));
    testEntityManager.persist(data.getEmployee(3));
    testEntityManager.persist(data.getEmployee(4));
    testEntityManager.persist(data.getEmployee(5));
  }

  @Test
  void givenNoKeyword_whenFindAll_shouldReturnCorrectList() {
    Page<Employee> result = employeeRepository.findAll(employeePredicate.getPredicate(""), Pageable.unpaged());
    assertThat(result.getTotalElements()).isEqualTo(6);
  }

  @Test
  void givenKeyword_Bangun_whenFindAll_shouldReturnCorrectList() {
    Page<Employee> result = employeeRepository.findAll(employeePredicate.getPredicate("bangun"), Pageable.unpaged());
    assertThat(result.getTotalElements()).isEqualTo(1);
    assertThat(result.getContent().get(0).getFirstName()).isEqualTo(this.data.getEmployee(0).getFirstName());
  }

  @Test
  void givenKeyword_Rina_whenFindAll_shouldReturnCorrectList() {
    Page<Employee> result = employeeRepository.findAll(employeePredicate.getPredicate("rina"), Pageable.unpaged());
    assertThat(result.getTotalElements()).isEqualTo(1);
    assertThat(result.getContent().get(0).getFirstName()).isEqualTo(this.data.getEmployee(1).getFirstName());
  }

  @Test
  void givenKeyword_5555_whenFindAll_shouldReturnCorrectList() {
    Page<Employee> result = employeeRepository.findAll(employeePredicate.getPredicate("5555"), Pageable.unpaged());
    assertThat(result.getTotalElements()).isEqualTo(2);
  }

  @Test
  void givenKeyword_123_whenFindAll_shouldReturnCorrectList() {
    Page<Employee> result = employeeRepository.findAll(employeePredicate.getPredicate("123"), Pageable.unpaged());
    assertThat(result.getTotalElements()).isEqualTo(3);
  }

  @Test
  void givenKeyword_maildotcom_whenFindAll_shouldReturnCorrectList() {
    Page<Employee> result = employeeRepository.findAll(employeePredicate.getPredicate("mail.com"), Pageable.unpaged());
    assertThat(result.getTotalElements()).isEqualTo(6);
  }

  @Test
  void givenKeyword_gmaildotcom_whenFindAll_shouldReturnCorrectList() {
    Page<Employee> result = employeeRepository.findAll(employeePredicate.getPredicate("mail.com"), Pageable.unpaged());
    assertThat(result.getTotalElements()).isEqualTo(6);
  }

  @Test
  void givenKeyword_21_whenFindAll_shouldReturnCorrectList() {
    Page<Employee> result = employeeRepository.findAll(employeePredicate.getPredicate("21"), Pageable.unpaged());
    assertThat(result.getTotalElements()).isEqualTo(2);

    Page<Employee> result1 = employeeRepository.findAll(employeePredicate.getPredicate("6"), Pageable.unpaged());
    result1.forEach(r -> System.out.println(r.getFirstName()));
    assertThat(result1.getTotalElements()).isEqualTo(3);
  }

  @Test
  void givenKeyword_putr_whenFindAll_shouldReturnCorrectList() {
    Page<Employee> result = employeeRepository.findAll(employeePredicate.getPredicate("putr"), Pageable.unpaged());
    assertThat(result.getTotalElements()).isEqualTo(2);

    Page<Employee> result1 = employeeRepository.findAll(employeePredicate.getPredicate("PUTR"), Pageable.unpaged());
    assertThat(result1.getTotalElements()).isEqualTo(2);

    Page<Employee> result2 = employeeRepository.findAll(employeePredicate.getPredicate("pUTr"), Pageable.unpaged());
    assertThat(result2.getTotalElements()).isEqualTo(2);
  }

  @AfterEach
  void clear() {
    testEntityManager.remove(data.getEmployee(0));
    testEntityManager.remove(data.getEmployee(1));
    testEntityManager.remove(data.getEmployee(2));
    testEntityManager.remove(data.getEmployee(3));
    testEntityManager.remove(data.getEmployee(4));
    testEntityManager.remove(data.getEmployee(5));
    assertThat(employeeRepository.findAll().size()).isZero();
  }
}
