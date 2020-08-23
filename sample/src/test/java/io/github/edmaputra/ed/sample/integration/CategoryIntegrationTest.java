package io.github.edmaputra.ed.sample.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edmaputra.ed.edbase.util.ResponseUtil;
import io.github.edmaputra.ed.sample.SampleApplication;
import io.github.edmaputra.ed.sample.model.Category;
import io.github.edmaputra.ed.sample.repository.CategoryRepository;
import io.github.edmaputra.ed.sample.util.CategoryTestIntegrationUtil;
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
public class CategoryIntegrationTest {

  private static final String ENTITY_URL = "/api/v1/categories/";

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private CategoryRepository repository;

  private Category category;
  private String url;
  private CategoryTestIntegrationUtil testUtil;

  @BeforeEach
  void init() {
    category = new Category("Category 1");

    url = "http://localhost:" + port + ENTITY_URL;
    testUtil = new CategoryTestIntegrationUtil(url, restTemplate, objectMapper);
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
    Category category1 = new Category("food");
    Category category2 = new Category("FOOD");
    Category category3 = new Category("drink");
    Category category4 = new Category("hazard");

    testUtil.assertCreateAndSave(category1);
    testUtil.assertCreateAndSave(category2);
    testUtil.assertCreateAndSave(category3);
    testUtil.assertCreateAndSave(category4);

    testUtil.assertListResultWithQuickSearch("", category1, category2, category3, category4);
  }

  @Test
  @Order(2)
  public void givenRequestWithFilter_whenGetWithKeyword_thenReturnExpectedList() throws JSONException, IOException {
    Category category1 = new Category("food");
    Category category2 = new Category("FOOD");
    Category category3 = new Category("drink");
    Category category4 = new Category("hazard");

    testUtil.assertCreateAndSave(category1);
    testUtil.assertCreateAndSave(category2);
    testUtil.assertCreateAndSave(category3);
    testUtil.assertCreateAndSave(category4);

    testUtil.assertListResultWithQuickSearch("fo", category1, category2);
    testUtil.assertListResultWithQuickSearch("d", category1, category2, category3, category4);
    testUtil.assertListResultWithQuickSearch("xxxxx");
  }

  @Test
  @Order(10)
  public void givenId_whenGetById_thenDataNotFound() throws JSONException, JsonProcessingException {
    Map<String, List<String>> errors = new HashMap<>();
    errors.put("error", Collections.singletonList("Category not found"));
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
    testUtil.assertCreateAndSave(category);

    String expectedResponse = objectMapper.writeValueAsString(category);
    UUID id = category.getId();

    ResponseEntity<String> actualResponse = restTemplate.getForEntity(url + id, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    testUtil.assertSingle(expectedResponse, actualResponse.getBody());
  }

  @Test
  @Order(20)
  public void givenEntity_whenAdd_thenReturnSavedObject() throws IOException, JSONException {
    testUtil.assertCreateAndSave(category);
  }

  @Test
  @Order(21)
  public void givenEntity_whenAdd_thenThrowIllegalArgumentException() throws JsonProcessingException, JSONException {
    Category newEntity = new Category("");

    Map<String, List<String>> errors = new HashMap<>();
    errors.put("name", Arrays.asList("must not be blank", "Length should be between 2 - 120 character"));
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse
        (errors, HttpStatus.BAD_REQUEST).getBody());

    ResponseEntity<String> actualResponse =
        restTemplate.postForEntity(url, newEntity, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    JSONAssert.assertEquals(expectedResponse, actualResponse.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  @Order(40)
  public void givenEntity_whenUpdate_thenReturnUpdatedObject() throws IOException, JSONException, InterruptedException {
    testUtil.assertCreateAndSave(category);

    Category updatedEntity = new Category();
    BeanUtils.copyProperties(category, updatedEntity);
    updatedEntity.setName("Food");
    String expectedResponse = objectMapper.writeValueAsString(updatedEntity);

    Thread.sleep(1000);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Category> entity = new HttpEntity<>(updatedEntity, headers);

    ResponseEntity<String> actualResponse = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    Customization[] customizations = new Customization[]{
        new Customization("id", (actualValue, expectedValue) -> actualValue.toString().equals(category.getId().toString())),
        new Customization("createTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isEqual(category.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("creator", (actualValue, expectedValue) -> actualValue.equals(category.getCreator())),
        new Customization("updateTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isAfter(category.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("updater", (actualValue, expectedValue) -> notNull(actualValue))
    };
    testUtil.assertSingle(expectedResponse, actualResponse.getBody(), customizations);
  }

  @Test
  @Order(41)
  public void givenEntity_whenUpdate_thenDataNotFound() throws IOException, JSONException {
    testUtil.assertCreateAndSave(category);

    Category updatedEntity = new Category();
    BeanUtils.copyProperties(category, updatedEntity);
    updatedEntity.setId(UUID.randomUUID());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Category> entity = new HttpEntity<>(updatedEntity, headers);

    Map<String, List<String>> errors = new HashMap<>();
    errors.put("error", Collections.singletonList("Category not found"));
    String expectedResponse = objectMapper.writeValueAsString(ResponseUtil.createExceptionResponse
        (errors, HttpStatus.NOT_FOUND).getBody());

    ResponseEntity<String> actualResponse = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    JSONAssert.assertEquals(expectedResponse, actualResponse.getBody(), JSONCompareMode.LENIENT);
  }

  @Test
  @Order(60)
  public void givenEntity_whenDelete_thenReturnDeletedObject() throws IOException, JSONException, InterruptedException {
    testUtil.assertCreateAndSave(category);

    Category deleteEntity = new Category();
    BeanUtils.copyProperties(category, deleteEntity);

    Thread.sleep(1000);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Category> entity = new HttpEntity<>(deleteEntity, headers);

    deleteEntity.setDeleteFlag(true);
    String expectedResponse = objectMapper.writeValueAsString(deleteEntity);

    ResponseEntity<String> actualResponse = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    Customization[] customizations = new Customization[]{
        new Customization("id", (actualValue, expectedValue) -> actualValue.toString().equals(category.getId().toString())),
        new Customization("createTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isEqual(category.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("creator", (actualValue, expectedValue) -> actualValue.equals(category.getCreator())),
        new Customization("updateTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isAfter(category.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("updater", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("deleteTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isAfter(category.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("deleteBy", (actualValue, expectedValue) -> notNull(actualValue)),
    };
    testUtil.assertSingle(expectedResponse, actualResponse.getBody(), customizations);
  }

  @Test
  @Order(61)
  public void givenEntity_whenDelete_thenDataNotFound() throws IOException, JSONException {
    testUtil.assertCreateAndSave(category);

    Category deleteEntity = new Category();
    BeanUtils.copyProperties(category, deleteEntity);
    deleteEntity.setId(UUID.randomUUID());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Category> entity = new HttpEntity<>(deleteEntity, headers);

    Map<String, List<String>> errors = new HashMap<>();
    errors.put("error", Collections.singletonList("Category not found"));
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
