package io.github.edmaputra.ed.sample.service.impl;

import io.github.edmaputra.ed.edbase.exception.CrudOperationException;
import io.github.edmaputra.ed.edbase.exception.DataEmptyException;
import io.github.edmaputra.ed.edbase.exception.DataNotFoundException;
import io.github.edmaputra.ed.edbase.model.Gender;
import io.github.edmaputra.ed.edbase.model.MaritalStatus;
import io.github.edmaputra.ed.sample.DataInit;
import io.github.edmaputra.ed.sample.model.Employee;
import io.github.edmaputra.ed.sample.predicate.EmployeePredicate;
import io.github.edmaputra.ed.sample.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

  @Mock
  private EmployeeRepository repository;

  @Mock
  EmployeePredicate predicate;

  @Mock
  AuditorAware<String> auditorAware;

  @InjectMocks
  private EmployeeServiceImpl service;

  private List<Employee> employees;
  private Page<Employee> employeesPage;
  private Employee e1, e2, e0;

  @BeforeEach
  void init() {
    DataInit init = new DataInit();
    employees = new ArrayList<>();

    e0 = init.getEmployee(0);
    e1 = init.getEmployee(1);
    e2 = init.getEmployee(2);

    employees = init.getAllEmployee();

    employeesPage = new PageImpl<>(employees);

    service.setAuditorAware(auditorAware);
  }


  @Test
  void givenPageableUnpaged_whenGet_thenReturnExpectedCollection() throws DataEmptyException, CrudOperationException {
    doReturn(employeesPage).when(repository).findAll(any(Pageable.class));

    Page<Employee> pages = service.get(Pageable.unpaged());

    assertThat(pages.getSize()).isEqualTo(6);
  }

  @Test
  void givenPageable_whenGet_thenReturnExpectedCollection() throws DataEmptyException, CrudOperationException {
    PageRequest pageRequest = PageRequest.of(0, 3);
    employeesPage = new PageImpl<>(employees, pageRequest, 6);

    doReturn(employeesPage).when(repository).findAll(any(Pageable.class));

    Page<Employee> pages = service.get(pageRequest);

    assertThat(pages.getSize()).isEqualTo(3);
    assertThat(pages.getTotalPages()).isEqualTo(2);
    assertThat(pages.getTotalElements()).isEqualTo(6);
  }

  @Test
  void givenPageable_whenGet_thenThrowException() {
    Page<Employee> pageEmpty = new PageImpl<>(new ArrayList<>());
    doReturn(pageEmpty).when(repository).findAll(any(Pageable.class));

    assertThrows(DataEmptyException.class, () -> service.get(Pageable.unpaged()));
  }

  @Test
  void givenNullPageable_whenGet_thenThrowException() {
    assertThrows(CrudOperationException.class, () -> service.get(null));
  }

  @Test
  void givenId_whenGetOne_thenReturnExpectedObject() throws DataNotFoundException, CrudOperationException {
    doReturn(Optional.of(e0)).when(repository).findById(anyLong());

    Employee employee = service.getOne(0L);

    assertThat(employee.getFirstName()).isEqualTo("Bangun");
    assertThat(employee.getMiddleName()).isEqualTo("Edma");
    assertThat(employee.getLastName()).isEqualTo("Saputra");
    assertThat(employee.getGender()).isEqualTo(Gender.MALE);
    assertThat(employee.getMaritalStatus()).isEqualTo(MaritalStatus.MARRIED);
    assertThat(employee.getBirthDate()).isEqualTo(LocalDate.of(1990, Month.AUGUST, 1));
    assertThat(employee.getPhoneNumber()).isEqualTo("08511234578");
    assertThat(employee.getEmail()).isEqualTo("bangun.edma.saputra@gmail.com");
  }

  @Test
  void givenNullId_whenGetOne_thenThrowException() {
    assertThrows(CrudOperationException.class, () -> service.getOne(null));
  }

  @Test
  void givenId_whenGetOne_thenThrowException() {
    doReturn(Optional.empty()).when(repository).findById(anyLong());

    assertThrows(DataNotFoundException.class, () -> service.getOne(1L));
  }

  @Test
  void givenE0Employee_whenAdd_thenReturnExpectedObject() throws CrudOperationException {
    doReturn(e0).when(repository).save(any(Employee.class));

    Employee employee = service.add(e0);

    assertThat(employee.getFirstName()).isEqualTo("Bangun");
    assertThat(employee.getMiddleName()).isEqualTo("Edma");
    assertThat(employee.getLastName()).isEqualTo("Saputra");
    assertThat(employee.getGender()).isEqualTo(Gender.MALE);
    assertThat(employee.getMaritalStatus()).isEqualTo(MaritalStatus.MARRIED);
    assertThat(employee.getBirthDate()).isEqualTo(LocalDate.of(1990, Month.AUGUST, 1));
    assertThat(employee.getPhoneNumber()).isEqualTo("08511234578");
    assertThat(employee.getEmail()).isEqualTo("bangun.edma.saputra@gmail.com");
  }

  @Test
  void givenNullEmployee_whenAdd_thenThrowException() {
    assertThrows(CrudOperationException.class, () -> service.add(null));
  }

  @Test
  void givenE0Employee_whenUpdate_thenReturnExpectedObject() throws DataNotFoundException, CrudOperationException {
    e0.setId(2L);
    doReturn(Optional.of(e0)).when(repository).findById(anyLong());
    doReturn(e0).when(repository).save(any(Employee.class));

    Employee employee = service.update(e0);

    assertThat(employee.getFirstName()).isEqualTo("Bangun");
    assertThat(employee.getMiddleName()).isEqualTo("Edma");
    assertThat(employee.getLastName()).isEqualTo("Saputra");
    assertThat(employee.getGender()).isEqualTo(Gender.MALE);
    assertThat(employee.getMaritalStatus()).isEqualTo(MaritalStatus.MARRIED);
    assertThat(employee.getBirthDate()).isEqualTo(LocalDate.of(1990, Month.AUGUST, 1));
    assertThat(employee.getPhoneNumber()).isEqualTo("08511234578");
    assertThat(employee.getEmail()).isEqualTo("bangun.edma.saputra@gmail.com");
  }

  @Test
  void givenNullEmployee_whenUpdate_thenReturnExpectedObject() {
    assertThrows(CrudOperationException.class, () -> service.update(null));
  }

  @Test
  void givenId_whenUpdate_thenThrowException() {
    e0.setId(2L);
    doReturn(Optional.empty()).when(repository).findById(anyLong());
    assertThrows(DataNotFoundException.class, () -> service.update(e0));
  }

  @Test
  void givenId_whenDelete_thenReturnExpectedObject() throws DataNotFoundException, CrudOperationException {
    e0.setId(2L);
    doReturn(Optional.of(e0)).when(repository).findById(anyLong());
    doReturn(e0).when(repository).save(any(Employee.class));
    doReturn(Optional.of("anonymous")).when(auditorAware).getCurrentAuditor();

    Employee employee = service.delete(e0);

    assertThat(employee.getFirstName()).isEqualTo("Bangun");
    assertThat(employee.isDeleteFlag()).isTrue();
  }

  @Test
  void givenId_whenHardDelete_thenReturnDeletedObject() throws CrudOperationException, DataNotFoundException {
    e0.setId(1L);
    doReturn(Optional.of(e0)).when(repository).findById(anyLong());

    Employee employee = service.hardDelete(e0);

    assertThat(employee.getFirstName()).isEqualTo("Bangun");
  }

  @Test
  void givenNullId_whenDelete_thenThrowException() {
    assertThrows(CrudOperationException.class, () -> service.delete(null));
  }

  @Test
  void givenId_whenDelete_thenThrowException() {
    e0.setId(2L);
    doReturn(Optional.empty()).when(repository).findById(anyLong());
    assertThrows(DataNotFoundException.class, () -> service.delete(e0));
  }

}
