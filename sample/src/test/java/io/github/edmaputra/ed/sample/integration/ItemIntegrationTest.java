package io.github.edmaputra.ed.sample.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edmaputra.ed.edbase.util.ResponseUtil;
import io.github.edmaputra.ed.sample.SampleApplication;
import io.github.edmaputra.ed.sample.model.Category;
import io.github.edmaputra.ed.sample.model.Item;
import io.github.edmaputra.ed.sample.repository.CategoryRepository;
import io.github.edmaputra.ed.sample.repository.ItemRepository;
import io.github.edmaputra.ed.sample.util.CategoryTestIntegrationUtil;
import io.github.edmaputra.ed.sample.util.ItemTestIntegrationUtil;
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
public class ItemIntegrationTest {

  private static final String ENTITY_URL = "/items/";
  private static final String CATEGORY_URL = "/categories/";

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ItemRepository repository;

  @Autowired
  private CategoryRepository categoryRepository;

  private Category category;
  private Item item;
  private String url, categoryUrl;
  private ItemTestIntegrationUtil testUtil;
  private CategoryTestIntegrationUtil categoryTestUtil;

  @BeforeEach
  void init() throws IOException, JSONException {
    url = "http://localhost:" + port + ENTITY_URL;
    categoryUrl = "http://localhost:" + port + CATEGORY_URL;
    testUtil = new ItemTestIntegrationUtil(url, restTemplate, objectMapper);
    categoryTestUtil = new CategoryTestIntegrationUtil(categoryUrl, restTemplate, objectMapper);

    category = new Category("Category 1");
    categoryTestUtil.assertCreateAndSave(category);

    item = new Item("Item 1");
    item.setCategory(category);
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
    Item item1 = new Item("food");
    item1.setCategory(category);
    Item item2 = new Item("FOOD");
    item2.setCategory(category);
    Item item3 = new Item("drink");
    item3.setCategory(category);
    Item item4 = new Item("hazard");
    item4.setCategory(category);

    testUtil.assertCreateAndSave(item1);
    testUtil.assertCreateAndSave(item2);
    testUtil.assertCreateAndSave(item3);
    testUtil.assertCreateAndSave(item4);

    testUtil.assertListResultWithQuickSearch("", item1, item2, item3, item4);
  }

  @Test
  @Order(2)
  public void givenRequestWithFilter_whenGetWithKeyword_thenReturnExpectedList() throws JSONException, IOException {
    Item item1 = new Item("food");
    item1.setCategory(category);
    Item item2 = new Item("FOOD");
    item2.setCategory(category);
    Item item3 = new Item("drink");
    item3.setCategory(category);
    Item item4 = new Item("hazard");
    item4.setCategory(category);

    testUtil.assertCreateAndSave(item1);
    testUtil.assertCreateAndSave(item2);
    testUtil.assertCreateAndSave(item3);
    testUtil.assertCreateAndSave(item4);

    testUtil.assertListResultWithQuickSearch("fo", item1, item2);
    testUtil.assertListResultWithQuickSearch("d", item1, item2, item3, item4);
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
    testUtil.assertCreateAndSave(item);

    String expectedResponse = objectMapper.writeValueAsString(item);
    UUID id = item.getId();

    ResponseEntity<String> actualResponse = restTemplate.getForEntity(url + id, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    testUtil.assertSingle(expectedResponse, actualResponse.getBody());
  }

  @Test
  @Order(20)
  public void givenEntity_whenAdd_thenReturnSavedObject() throws IOException, JSONException {
    testUtil.assertCreateAndSave(item);
  }

  @Test
  @Order(21)
  public void givenEntity_whenAdd_thenThrowIllegalArgumentException() throws JsonProcessingException, JSONException {
    Item newEntity = new Item("");

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
    testUtil.assertCreateAndSave(item);

    Item updatedEntity = new Item();
    BeanUtils.copyProperties(item, updatedEntity);
    updatedEntity.setName("Food");
    String expectedResponse = objectMapper.writeValueAsString(updatedEntity);

    Thread.sleep(1000);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Item> entity = new HttpEntity<>(updatedEntity, headers);

    ResponseEntity<String> actualResponse = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    Customization[] customizations = new Customization[]{
        new Customization("id", (actualValue, expectedValue) -> actualValue.toString().equals(item.getId().toString())),
        new Customization("createTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isEqual(item.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("creator", (actualValue, expectedValue) -> actualValue.equals(item.getCreator())),
        new Customization("updateTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isAfter(item.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("updater", (actualValue, expectedValue) -> notNull(actualValue))
    };
    testUtil.assertSingle(expectedResponse, actualResponse.getBody(), customizations);
  }

  @Test
  @Order(41)
  public void givenEntity_whenUpdate_thenDataNotFound() throws IOException, JSONException {
    testUtil.assertCreateAndSave(item);

    Item updatedEntity = new Item();
    BeanUtils.copyProperties(item, updatedEntity);
    updatedEntity.setId(UUID.randomUUID());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Item> entity = new HttpEntity<>(updatedEntity, headers);

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
  public void givenEntity_whenDelete_thenReturnDeletedObject() throws IOException, JSONException, InterruptedException {
    testUtil.assertCreateAndSave(item);

    Item deleteEntity = new Item();
    BeanUtils.copyProperties(item, deleteEntity);

    Thread.sleep(1000);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Item> entity = new HttpEntity<>(deleteEntity, headers);

    deleteEntity.setDeleteFlag(true);
    String expectedResponse = objectMapper.writeValueAsString(deleteEntity);

    ResponseEntity<String> actualResponse = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    Customization[] customizations = new Customization[]{
        new Customization("id", (actualValue, expectedValue) -> actualValue.toString().equals(item.getId().toString())),
        new Customization("createTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isEqual(item.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("creator", (actualValue, expectedValue) -> actualValue.equals(item.getCreator())),
        new Customization("updateTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isAfter(item.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("updater", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("deleteTime", (actualValue, expectedValue) -> ZonedDateTime.parse(actualValue.toString()).isAfter(item.getCreateTime().withZoneSameInstant(ZoneId.systemDefault()))),
        new Customization("deleteBy", (actualValue, expectedValue) -> notNull(actualValue)),
    };
    testUtil.assertSingle(expectedResponse, actualResponse.getBody(), customizations);
  }

  @Test
  @Order(61)
  public void givenEntity_whenDelete_thenDataNotFound() throws IOException, JSONException {
    testUtil.assertCreateAndSave(item);

    Item deleteEntity = new Item();
    BeanUtils.copyProperties(item, deleteEntity);
    deleteEntity.setId(UUID.randomUUID());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Item> entity = new HttpEntity<>(deleteEntity, headers);

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
    categoryRepository.deleteAll();
  }

}
