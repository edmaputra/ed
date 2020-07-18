package io.github.edmaputra.ed.sample.controller;

import io.github.edmaputra.ed.sample.model.Employee;
import io.github.edmaputra.ed.sample.service.EmployeeService;
import io.github.edmaputra.ed.edbase.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController extends BaseController<Employee, Long> {

  public EmployeeController(EmployeeService service) {
    super(service);
  }
}
