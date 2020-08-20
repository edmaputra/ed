package io.github.edmaputra.ed.sample.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edmaputra.ed.sample.model.Employee;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class EmployeeTestIntegrationUtil extends TestIntegrationUtil<Employee> {

  public EmployeeTestIntegrationUtil(String baseUrl, TestRestTemplate restTemplate, ObjectMapper objectMapper) {
    super(baseUrl, restTemplate, objectMapper);
  }
}
