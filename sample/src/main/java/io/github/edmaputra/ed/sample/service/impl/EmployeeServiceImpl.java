package io.github.edmaputra.ed.sample.service.impl;

import io.github.edmaputra.ed.edbase.service.impl.BaseServiceImpl;
import io.github.edmaputra.ed.sample.model.Employee;
import io.github.edmaputra.ed.sample.predicate.EmployeePredicate;
import io.github.edmaputra.ed.sample.repository.EmployeeRepository;
import io.github.edmaputra.ed.sample.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, UUID> implements EmployeeService {

  public EmployeeServiceImpl(EmployeeRepository repository, EmployeePredicate predicate) {
    super(repository, predicate);
  }
}
