package io.github.edmaputra.ed.sample.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edmaputra.ed.core.model.Gender;
import io.github.edmaputra.ed.core.model.MaritalStatus;
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
  @Order(1000)
  public void testGetAllEmployees_shouldNotFound() throws JSONException {
    String expected = "{statusCode:404, status:\"NOT_FOUND\", message:\"Data Empty\"}";

    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    JSONAssert.assertEquals(expected, response.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  @Order(1001)
  public void testGetOneEmployee_shouldNotFound() throws JSONException {
    String expected = "{statusCode:404, status:\"NOT_FOUND\", message:\"Data Not Found\"}";
    long id = 1;

    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees/" + id, String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    JSONAssert.assertEquals(expected, response.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  @Order(1002)
  public void testGetOneEmployeeWithNonNumber_shouldBadRequest() throws JSONException {
    String expected = "{statusCode:404, status:\"NOT_FOUND\", message:\"Data Not Found\"}";

    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees/null", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @Order(1003)
  @Sql(scripts = {"classpath:sql/add_data_1.sql"})
  public void testGetAllEmployees_shouldFound() throws JSONException {
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    JSONAssert.assertEquals("{content:[4]}", response.getBody(), new ArraySizeComparator(JSONCompareMode.LENIENT));
  }

  @Test
  @Order(1004)
  public void testGetAllEmployeesWithFilter_shouldNotFound() throws JSONException {
    String expected = "{statusCode:404, status:\"NOT_FOUND\", message:\"Data Empty\"}";

    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees?filter=wkekek", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    JSONAssert.assertEquals(expected, response.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  @Order(1005)
  @Sql(scripts = {"classpath:sql/add_data_1.sql"})
  public void testGetAllEmployeesWithFilter_shouldFound() throws JSONException {
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees?filter=bangun", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    JSONAssert.assertEquals("{content:[1]}", response.getBody(), new ArraySizeComparator(JSONCompareMode.LENIENT));
  }


  @Test
  @Order(1006)
  public void testAddEmployee_shouldCreatedAndReturnCorrectObject() throws JsonProcessingException {
    Employee newEmployee = new Employee(
        "Muhammad", "", "Michael", Gender.MALE, MaritalStatus.SINGLE, "Bandung",
        LocalDate.of(2000, Month.APRIL, 10), "11223344556678", ""
    );

    ResponseEntity<String> response =
        restTemplate.postForEntity("http://localhost:" + port + "/employees/", newEmployee, String.class);
    Employee actualEmployee = objectMapper.readValue(response.getBody(), Employee.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertEmployeeAttribute(actualEmployee, "Muhammad", "", "Michael", Gender.MALE,
        MaritalStatus.SINGLE, "Bandung", LocalDate.of(2000, Month.APRIL, 10), "11223344556678", "");

  }

  @Test
  @Order(1007)
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
    HttpEntity<Employee> entity = new HttpEntity<Employee>(savedEmployee, headers);

    ResponseEntity<String> updateResponse =
        restTemplate.exchange("http://localhost:" + port + "/employees/", HttpMethod.PUT, entity, String.class);
    Employee updatedEmployee = objectMapper.readValue(updateResponse.getBody(), Employee.class);

    assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertEmployeeAttribute(updatedEmployee,
        "Adiba",
        "Dzakira",
        "Diatra",
        Gender.FEMALE,
        MaritalStatus.SINGLE,
        "Tarakan",
        LocalDate.of(2019, 2, 2),
        "123456789012",
        "adiba.diatra@mail.id");
  }

  @Test
  @Order(1008)
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
    assertThat(deletedEmployee.getId()).isEqualTo(employeeId);
    assertThat(deletedEmployee.isRecorded()).isFalse();
  }

  private void assertEmployeeAttribute(
      Employee employee, String firstName, String middleName, String lastName,
      Gender gender, MaritalStatus maritalStatus, String birthPlace, LocalDate birthDate,
      String phoneNumber, String email
  ){
    assertThat(employee.getFirstName()).isEqualToIgnoringCase(firstName);
    assertThat(employee.getMiddleName()).isEqualToIgnoringCase(middleName);
    assertThat(employee.getLastName()).isEqualToIgnoringCase(lastName);
    assertThat(employee.getGender()).isEqualTo(gender);
    assertThat(employee.getMaritalStatus()).isEqualTo(maritalStatus);
    assertThat(employee.getBirthPlace()).isEqualToIgnoringCase(birthPlace);
    assertThat(employee.getBirthDate()).isEqualTo(birthDate);
    assertThat(employee.getPhoneNumber()).isEqualToIgnoringCase(phoneNumber);
    assertThat(employee.getEmail()).isEqualToIgnoringCase(email);
  }

  private void checkCurrentData() {
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/employees", String.class);
    System.out.println(response.getBody());
  }

}
