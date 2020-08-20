package io.github.edmaputra.ed.sample.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edmaputra.ed.edbase.model.Gender;
import io.github.edmaputra.ed.edbase.model.MaritalStatus;
import io.github.edmaputra.ed.edbase.util.ResponseUtil;
import io.github.edmaputra.ed.sample.SampleApplication;
import io.github.edmaputra.ed.sample.model.Employee;
import io.github.edmaputra.ed.sample.repository.EmployeeRepository;
import io.github.edmaputra.ed.sample.util.EmployeeTestIntegrationUtil;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.github.edmaputra.ed.sample.util.TestUtil.notNull;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    classes = SampleApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeIntegrationTest {

  private static final String EMPLOYEE_URL = "/employees/";

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private EmployeeRepository repository;

  private Employee employee;
  private String url;
  private EmployeeTestIntegrationUtil testUtil;

  @BeforeEach
  void init() {
    employee = new Employee(
        "Muhammad", "", "Michael", Gender.MALE, MaritalStatus.SINGLE, "Bandung",
        LocalDate.of(2000, Month.APRIL, 10), "11223344556678", ""
    );

    url = "http://localhost:" + port+ EMPLOYEE_URL;
    testUtil = new EmployeeTestIntegrationUtil(url, restTemplate, objectMapper);
  }

  @Test
  @Order(0)
  public void givenRequest_whenGetWithKeyword_thenReturnEmptyContent() throws JSONException, JsonProcessingException {
    String expectedResponse = objectMapper.writeValueAsString(
        new PageImpl<>(new ArrayList<>(), PageRequest.of(0, 20, Sort.unsorted()), 0));

    ResponseEntity<String> actualResponse = restTemplate.getForEntity(url, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    JSONAssert.assertEquals(expectedResponse, actualResponse.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  @Order(1)
  public void givenRequestWithoutFilter_whenGetWithKeyword_thenReturnExpectedList() throws JSONException, IOException {
    Employee employee1 = new Employee(
        "Bangun", "Edma", "Saputra", Gender.MALE, MaritalStatus.MARRIED, "Kota Bangun",
        LocalDate.of(1990, Month.JULY, 8), "085112345678", "bangun.edma@rocket.com"
    );
    Employee employee2 = new Employee(
        "Rina", "", "Wibowo", Gender.FEMALE, MaritalStatus.SINGLE, "Bandung",
        LocalDate.of(1991, Month.SEPTEMBER, 5), "08530987654", "rina.wibowo@yahoo.com"
    );
    Employee employee3 = new Employee(
        "Alex", "Rudolph", "Maningger", Gender.MALE, MaritalStatus.DIVORCEE, "Espanyol",
        LocalDate.of(1988, Month.FEBRUARY, 16), "085555550000", "maningger@mail.com"
    );
    Employee employee4 = new Employee(
        "Ahmad", "", "Yusuf", Gender.MALE, MaritalStatus.SINGLE, "Makassar",
        LocalDate.of(1982, Month.MAY, 21), "08881234578", "ahmad.yusuf@gmail.com"
    );

    testUtil.assertCreateAndSave(employee1);
    testUtil.assertCreateAndSave(employee2);
    testUtil.assertCreateAndSave(employee3);
    testUtil.assertCreateAndSave(employee4);

    testUtil.assertListResultWithQuickSearch("", employee1, employee2, employee3, employee4);
  }

  @Test
  @Order(2)
  public void givenRequestWithFilter_whenGetWithKeyword_thenReturnExpectedList() throws JSONException, IOException {
    Employee employee1 = new Employee(
        "Bangun", "Edma", "Saputra", Gender.MALE, MaritalStatus.MARRIED, "Kota Bangun",
        LocalDate.of(1990, Month.JULY, 8), "085112345678", "bangun.edma@rocket.com"
    );
    Employee employee2 = new Employee(
        "Rina", "", "Wibowo", Gender.FEMALE, MaritalStatus.SINGLE, "Bandung",
        LocalDate.of(1991, Month.SEPTEMBER, 5), "08530987654", "rina.wibowo@yahoo.com"
    );
    Employee employee3 = new Employee(
        "Alex", "Rudolph", "Maningger", Gender.MALE, MaritalStatus.DIVORCEE, "Espanyol",
        LocalDate.of(1988, Month.FEBRUARY, 16), "085555550000", "maningger@mail.com"
    );
    Employee employee4 = new Employee(
        "Ali", "", "Yusuf", Gender.MALE, MaritalStatus.SINGLE, "Makassar",
        LocalDate.of(1982, Month.MAY, 21), "+628881234578", "ahmad.yusuf@gmail.com"
    );

    testUtil.assertCreateAndSave(employee1);
    testUtil.assertCreateAndSave(employee2);
    testUtil.assertCreateAndSave(employee3);
    testUtil.assertCreateAndSave(employee4);

    testUtil.assertListResultWithQuickSearch("bangun", employee1);
    testUtil.assertListResultWithQuickSearch("mail", employee3, employee4);
    testUtil.assertListResultWithQuickSearch("ban", employee1, employee2);
    testUtil.assertListResultWithQuickSearch("234", employee1, employee4);
    testUtil.assertListResultWithQuickSearch("08", employee1, employee2, employee3);
    testUtil.assertListResultWithQuickSearch("Al", employee4, employee3);
    testUtil.assertListResultWithQuickSearch("xxxxx");
  }

  @Test
  @Order(10)
  public void givenId_whenGetById_thenDataNotFound() throws JSONException, JsonProcessingException {
    Map<String, List<String>> errors = new HashMap<>();
    errors.put("error", Collections.singletonList("Data Not Found"));
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse
        (errors, HttpStatus.NOT_FOUND).getBody());
    UUID id = UUID.randomUUID();

    ResponseEntity<String> actualResponse = restTemplate.getForEntity(url + id, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    JSONAssert.assertEquals(expectedResponse, actualResponse.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  @Order(11)
  public void givenId_whenGetById_thenReturnExpectedObject() throws JSONException, IOException {
    testUtil.assertCreateAndSave(employee);

    String expectedResponse = objectMapper.writeValueAsString(employee);
    UUID id = employee.getId();

    ResponseEntity<String> actualResponse = restTemplate.getForEntity(url + id, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    testUtil.assertSingle(expectedResponse, actualResponse.getBody());
  }

  @Test
  @Order(20)
  public void givenEmployee_whenAdd_thenReturnSavedObject() throws IOException, JSONException {
    testUtil.assertCreateAndSave(employee);
  }

  @Test
  @Order(21)
  public void givenEmployee_whenAdd_thenThrowIllegalArgumentException() throws JsonProcessingException, JSONException {
    Employee newEmployee = new Employee(
        "M", "", "", null, null, "",
        LocalDate.of(2000, Month.APRIL, 10), "11223344556678", "asas"
    );

    Map<String, List<String>> errors = new HashMap<>();
    errors.put("firstName", Collections.singletonList("Length should be between 2 - 120"));
    errors.put("lastName", Arrays.asList("must not be blank", "Length should be between 2 - 120"));
    errors.put("birthPlace", Arrays.asList("must not be blank", "Length should be 1 - 100"));
    errors.put("gender", Collections.singletonList("must not be null"));
    errors.put("maritalStatus", Collections.singletonList("must not be null"));
    errors.put("email", Collections.singletonList("must be a well-formed email address"));
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse
        (errors, HttpStatus.BAD_REQUEST).getBody());

    ResponseEntity<String> actualResponse =
        restTemplate.postForEntity(url, newEmployee, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    JSONAssert.assertEquals(expectedResponse, actualResponse.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  @Order(40)
  public void givenEmployee_whenUpdate_thenReturnUpdatedObject() throws IOException, JSONException, InterruptedException {
    testUtil.assertCreateAndSave(employee);

    Employee updatedEmployee = new Employee();
    BeanUtils.copyProperties(employee, updatedEmployee);
    updatedEmployee.setFirstName("Bangun");
    updatedEmployee.setMiddleName("Edma");
    updatedEmployee.setLastName("Saputra");
    updatedEmployee.setBirthDate(LocalDate.of(1990, Month.JULY, 8));
    updatedEmployee.setBirthPlace("Kota Bangun");
    updatedEmployee.setEmail("bangun@mail.com");
    String expectedResponse = objectMapper.writeValueAsString(updatedEmployee);

    Thread.sleep(1000);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Employee> entity = new HttpEntity<>(updatedEmployee, headers);

    ResponseEntity<String> actualResponse = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    Customization[] customizations = new Customization[] {
        new Customization("id", (actualValue, expectedValue) -> actualValue.toString().equals(employee.getId().toString())),
        new Customization("createTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isEqual(employee.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("creator", (actualValue, expectedValue) -> actualValue.equals(employee.getCreator())),
        new Customization("updateTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isAfter(employee.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("updater", (actualValue, expectedValue) -> notNull(actualValue))
    };
    testUtil.assertSingle(expectedResponse, actualResponse.getBody(), customizations);
  }

  @Test
  @Order(41)
  public void givenEmployee_whenUpdate_thenDataNotFound() throws IOException, JSONException {
    testUtil.assertCreateAndSave(employee);

    Employee updatedEmployee = new Employee();
    BeanUtils.copyProperties(employee, updatedEmployee);
    updatedEmployee.setId(UUID.randomUUID());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Employee> entity = new HttpEntity<>(updatedEmployee, headers);

    Map<String, List<String>> errors = new HashMap<>();
    errors.put("error", Collections.singletonList("Data Not Found"));
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse
        (errors, HttpStatus.NOT_FOUND).getBody());

    ResponseEntity<String> actualResponse = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    JSONAssert.assertEquals(expectedResponse, actualResponse.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  @Order(60)
  public void givenEmployee_whenDelete_thenReturnDeletedObject() throws IOException, JSONException, InterruptedException {
    testUtil.assertCreateAndSave(employee);

    Employee deleteEmployee = new Employee();
    BeanUtils.copyProperties(employee, deleteEmployee);

    Thread.sleep(1000);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Employee> entity = new HttpEntity<>(deleteEmployee, headers);

    deleteEmployee.setDeleteFlag(true);
    String expectedResponse = objectMapper.writeValueAsString(deleteEmployee);

    ResponseEntity<String> actualResponse = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    Customization[] customizations = new Customization[] {
        new Customization("id", (actualValue, expectedValue) -> actualValue.toString().equals(employee.getId().toString())),
        new Customization("createTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isEqual(employee.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("creator", (actualValue, expectedValue) -> actualValue.equals(employee.getCreator())),
        new Customization("updateTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isAfter(employee.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("updater", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("deleteTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isAfter(employee.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("deleteBy", (actualValue, expectedValue) -> notNull(actualValue)),
    };
    testUtil.assertSingle(expectedResponse, actualResponse.getBody(), customizations);
  }

  @Test
  @Order(61)
  public void givenEmployee_whenDelete_thenDataNotFound() throws IOException, JSONException {
    testUtil.assertCreateAndSave(employee);

    Employee deleteEmployee = new Employee();
    BeanUtils.copyProperties(employee, deleteEmployee);
    deleteEmployee.setId(UUID.randomUUID());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Employee> entity = new HttpEntity<>(deleteEmployee, headers);

    Map<String, List<String>> errors = new HashMap<>();
    errors.put("error", Collections.singletonList("Data Not Found"));
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse
        (errors, HttpStatus.NOT_FOUND).getBody());

    ResponseEntity<String> actualResponse = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    JSONAssert.assertEquals(expectedResponse, actualResponse.getBody(), JSONCompareMode.LENIENT);
  }

    @AfterEach
  public void clear() {
    repository.deleteAll();
  }

}
