package io.github.edmaputra.ed.sample.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.github.edmaputra.ed.edbase.model.BaseIdEntity;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class TestIntegrationUtil<E extends BaseIdEntity<UUID>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestIntegrationUtil.class);

  private final Class<E> clazz;
  private final String baseUrl;
  private final TestRestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  private Customization[] singleCustomAssert;
  private Customization[] collectionCustomAssert;

  public void setSingleCustomAssert(Customization[] singleCustomAssert) {
    this.singleCustomAssert = singleCustomAssert;
  }

  public void setCollectionCustomAssert(Customization[] collectionCustomAssert) {
    this.collectionCustomAssert = collectionCustomAssert;
  }

  public TestIntegrationUtil(String baseUrl, TestRestTemplate restTemplate, ObjectMapper objectMapper) {
    this.baseUrl = baseUrl;
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
    Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(getClass(), TestIntegrationUtil.class);
    this.clazz = (Class<E>) (classes != null ? classes[0] : null);

    singleCustomAssert = new Customization[]{
        new Customization("id", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("createTime", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("creator", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("updateTime", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("updater", (actualValue, expectedValue) -> notNull(actualValue)),
    };

    collectionCustomAssert = new Customization[]{
        new Customization("content[*].createTime", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("content[*].creator", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("content[*].updateTime", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("content[*].updater", (actualValue, expectedValue) -> notNull(actualValue)),
    };
  }

  public void assertSingle(String expectedResponse, String actualResponse) throws JSONException {
    assertSingle(expectedResponse, actualResponse, singleCustomAssert);

  }

  public void assertSingle(String expectedResponse, String actualResponse, Customization[] customizations) throws JSONException {
    JSONAssert.assertEquals(expectedResponse, actualResponse,
        new CustomComparator(JSONCompareMode.LENIENT, customizations));

  }

  public void assertCreateAndSave(E employee) throws JSONException, IOException {
    String expectedResponse = objectMapper.writeValueAsString(employee);

    ResponseEntity<String> actualResponse = restTemplate.postForEntity(baseUrl, employee, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    JSONAssert.assertEquals(expectedResponse, Objects.requireNonNull(actualResponse.getBody()),
        new CustomComparator(JSONCompareMode.LENIENT, singleCustomAssert));

    JsonNode data = objectMapper.readTree(actualResponse.getBody()).path("id");
    UUID actualResult = objectMapper.readerFor(UUID.class).readValue(data);
    employee.setId(actualResult);
  }

  public void assertCreateAndExpect(E employee, String expectedResponse) throws JSONException, IOException {
    ResponseEntity<String> actualResponse = restTemplate.postForEntity(baseUrl, employee, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    JSONAssert.assertEquals(expectedResponse, Objects.requireNonNull(actualResponse.getBody()), JSONCompareMode.LENIENT);
  }
  public void assertListResultWithQuickSearch(String quickSearch, E... e)
      throws JsonProcessingException, JSONException {
    assertListResultWithQuickSearch(quickSearch, collectionCustomAssert, e);

  }
  public void assertListResultWithQuickSearch(String quickSearch, Customization[] customizations, E... e)
      throws JsonProcessingException, JSONException {
//    int totalPages = 0;
//    List<E> ds = new ArrayList<>();
//    if (e.length > 0) {
//      ds = Arrays.asList(e);
//      totalPages = e.length / request.getPageSize() + 1;
//    }

    Page<E> expectedData = new PageImpl<>(Arrays.asList(e), PageRequest.of(0, 20), e.length);
    String expectedResponse = objectMapper.writeValueAsString(expectedData);

    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl)
        .queryParam("filter", quickSearch);

    ResponseEntity<String> actualResponse = restTemplate.getForEntity(builder.build().encode().toString(), String.class);

    JSONAssert.assertEquals(expectedResponse, Objects.requireNonNull(actualResponse.getBody()),
        new CustomComparator(JSONCompareMode.LENIENT, customizations));
  }

  private List<E> extractDataContentToList(JsonNode data) throws IOException {
    ObjectReader reader = objectMapper.readerFor(objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    return reader.readValue(data);
  }

  private boolean notNull(Object object) {
    if (object instanceof String) {
      String value = (String) object;
      return !value.equals("null");
    }
    return Objects.nonNull(object);
  }
}
