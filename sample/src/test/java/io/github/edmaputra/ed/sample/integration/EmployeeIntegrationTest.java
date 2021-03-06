package io.github.edmaputra.ed.sample.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edmaputra.ed.edbase.model.Gender;
import io.github.edmaputra.ed.edbase.model.MaritalStatus;
import io.github.edmaputra.ed.edbase.util.ResponseUtil;
import io.github.edmaputra.ed.sample.SampleApplication;
import io.github.edmaputra.ed.sample.model.Employee;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.ArraySizeComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    classes = SampleApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = {"classpath:sql/drop_schema.sql", "classpath:sql/create_schema.sql"})
public class EmployeeIntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void init() {
  }

  @Test
  @Order(0)
  public void testGetAllEmployees_shouldNotFound() throws JSONException, JsonProcessingException {
    Map<String, List<String>> errors = new HashMap<>();
    errors.put("error", Collections.singletonList("Data Empty"));
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse
        (errors, HttpStatus.NOT_FOUND).getBody());

    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    JSONAssert.assertEquals(expectedResponse, response.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  @Order(1)
  public void testGetOneEmployee_shouldNotFound() throws JSONException, JsonProcessingException {
    Map<String, List<String>> errors = new HashMap<>();
    errors.put("error", Collections.singletonList("Data Not Found"));
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse
        (errors, HttpStatus.NOT_FOUND).getBody());
    long id = 1;

    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees/" + id, String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    JSONAssert.assertEquals(expectedResponse, response.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  @Order(3)
  @Sql(scripts = {"classpath:sql/add_data_1.sql"})
  public void testGetAllEmployees_shouldFound() throws JSONException {
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    JSONAssert.assertEquals("{content:[4]}", Objects.requireNonNull(response.getBody()), new ArraySizeComparator(JSONCompareMode.LENIENT));
  }

  @Test
  @Order(4)
  public void testGetAllEmployeesWithFilter_shouldNotFound() throws JSONException, JsonProcessingException {
    Map<String, List<String>> errors = new HashMap<>();
    errors.put("error", Collections.singletonList("Data Empty"));
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse
        (errors, HttpStatus.NOT_FOUND).getBody());
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees?filter=wkekek", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    JSONAssert.assertEquals(expectedResponse, response.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  @Order(5)
  @Sql(scripts = {"classpath:sql/add_data_1.sql"})
  public void testGetAllEmployeesWithFilter_shouldFound() throws JSONException {
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees?filter=bangun", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    JSONAssert.assertEquals("{content:[1]}", Objects.requireNonNull(response.getBody()), new ArraySizeComparator(JSONCompareMode.LENIENT));
  }

  @Test
  @Order(6)
  public void testAddEmployee_shouldCreatedAndReturnCorrectObject() throws JsonProcessingException {
    Employee newEmployee = new Employee(
        "Muhammad", "", "Michael", Gender.MALE, MaritalStatus.SINGLE, "Bandung",
        LocalDate.of(2000, Month.APRIL, 10), "11223344556678", ""
    );

    ResponseEntity<String> response =
        restTemplate.postForEntity("http://localhost:" + port + "/employees/", newEmployee, String.class);
    Employee actualEmployee = objectMapper.readValue(response.getBody(), Employee.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertExpectResultOfEmployeeData(actualEmployee, "Muhammad", "", "Michael", Gender.MALE,
        MaritalStatus.SINGLE, "Bandung", LocalDate.of(2000, Month.APRIL, 10), "11223344556678",
        "", OperationType.INSERT);
  }

  @Test
  @Order(7)
  @Sql(scripts = {"classpath:sql/add_data_2.sql"})
  public void testUpdateEmployee_shouldUpdateAndReturnCorrectObject() throws JsonProcessingException {
    checkCurrentData();
    long employeeId = 5;
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees/" + employeeId, String.class);
    Employee savedEmployee = objectMapper.readValue(response.getBody(), Employee.class);

    savedEmployee.setFirstName("Adiba");
    savedEmployee.setMiddleName("Dzakira");
    savedEmployee.setLastName("Diatra");
    savedEmployee.setGender(Gender.FEMALE);
    savedEmployee.setMaritalStatus(MaritalStatus.SINGLE);
    savedEmployee.setBirthDate(LocalDate.of(2019, 2, 2));
    savedEmployee.setBirthPlace("Tarakan");
    savedEmployee.setEmail("adiba.diatra@mail.id");
    savedEmployee.setPhoneNumber("123456789012");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Employee> entity = new HttpEntity<>(savedEmployee, headers);

    ResponseEntity<String> updateResponse =
        restTemplate.exchange("http://localhost:" + port + "/employees/", HttpMethod.PUT, entity, String.class);
    Employee updatedEmployee = objectMapper.readValue(updateResponse.getBody(), Employee.class);

    assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertExpectResultOfEmployeeData(updatedEmployee,
        "Adiba",
        "Dzakira",
        "Diatra",
        Gender.FEMALE,
        MaritalStatus.SINGLE,
        "Tarakan",
        LocalDate.of(2019, 2, 2),
        "123456789012",
        "adiba.diatra@mail.id", OperationType.UPDATE);
  }

  @Test
  @Order(8)
  @Sql(scripts = {"classpath:sql/add_data_3.sql"})
  public void testDeleteEmployee_shouldDeleteAndReturnCorrectObject() throws JsonProcessingException {
    checkCurrentData();
    long employeeId = 6;
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees/" + employeeId, String.class);
    Employee savedEmployee = objectMapper.readValue(response.getBody(), Employee.class);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Employee> entity = new HttpEntity<>(savedEmployee, headers);

    ResponseEntity<String> deleteResponse =
        restTemplate.exchange("http://localhost:" + port + "/employees/", HttpMethod.DELETE, entity, String.class);
    Employee deletedEmployee = objectMapper.readValue(deleteResponse.getBody(), Employee.class);

    assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertExpectResultOfEmployeeData(deletedEmployee,
        "Ahmad",
        "",
        "Yusuf",
        Gender.MALE,
        MaritalStatus.SINGLE,
        "Makassar",
        LocalDate.of(1982, Month.MAY, 21),
        "08881234578",
        "ahmad.yusuf@mail.com", OperationType.DELETE);
  }

  @Test
  @Order(9)
  public void testAddEmployee_shouldThrowIllegalArgumentException() throws JsonProcessingException, JSONException {
    Employee newEmployee = new Employee(
        "M", "", "", Gender.MALE, MaritalStatus.SINGLE, "Bandung",
        LocalDate.of(2000, Month.APRIL, 10), "11223344556678", "asas"
    );

    ResponseEntity<String> response =
        restTemplate.postForEntity("http://localhost:" + port + "/employees/", newEmployee, String.class);

    Map<String, List<String>> errors = new HashMap<>();
    errors.put("firstName", Collections.singletonList("Length should be between 2 - 120"));
    errors.put("lastName", Arrays.asList("Length should be between 2 - 120", "must not be blank"));
    errors.put("email", Collections.singletonList("must be a well-formed email address"));
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse
        (errors, HttpStatus.BAD_REQUEST).getBody());

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    JSONAssert.assertEquals(expectedResponse, response.getBody(), JSONCompareMode.LENIENT);
  }

  private void assertExpectResultOfEmployeeData(
      Employee employee, String firstName, String middleName, String lastName,
      Gender gender, MaritalStatus maritalStatus, String birthPlace, LocalDate birthDate,
      String phoneNumber, String email, OperationType operationType
  ) {
    assertThat(firstName).isEqualToIgnoringCase(employee.getFirstName());
    assertThat(middleName).isEqualToIgnoringCase(employee.getMiddleName());
    assertThat(lastName).isEqualToIgnoringCase(employee.getLastName());
    assertThat(gender).isEqualTo(employee.getGender());
    assertThat(maritalStatus).isEqualTo(employee.getMaritalStatus());
    assertThat(birthPlace).isEqualToIgnoringCase(birthPlace);
    assertThat(birthDate).isEqualTo(employee.getBirthDate());
    assertThat(phoneNumber).isEqualToIgnoringCase(employee.getPhoneNumber());
    assertThat(email).isEqualToIgnoringCase(employee.getEmail());

    if (operationType == OperationType.INSERT) {
      assertThat(employee.getCreateTime()).isNotNull();
      assertThat(employee.getCreator()).isNotNull();
    }

    if (operationType == OperationType.UPDATE) {
      assertThat(employee.getUpdateTime()).isNotNull();
      assertThat(employee.getUpdater()).isNotNull();
    }

    if (operationType == OperationType.DELETE) {
      assertThat(employee.isDeleteFlag()).isTrue();
      assertThat(employee.getDeleteTime()).isNotNull();
      assertThat(employee.getDeleteBy()).isNotNull();
    }
  }

  private void checkCurrentData() {
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees", String.class);
  }

  enum OperationType {

    INSERT, UPDATE, DELETE

  }

}
