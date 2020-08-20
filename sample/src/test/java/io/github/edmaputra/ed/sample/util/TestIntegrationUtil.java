package io.github.edmaputra.ed.sample.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edmaputra.ed.edbase.model.BaseIdEntity;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

import static io.github.edmaputra.ed.sample.util.TestUtil.notNull;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class TestIntegrationUtil<E extends BaseIdEntity<UUID>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestIntegrationUtil.class);

  private final Class<E> clazz;
  private final String baseUrl;
  private final TestRestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  private Customization[] singleCustomAssert;
  private Customization[] collectionCustomAssert;

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

  public void assertCreateAndSave(E inputObject) throws JSONException, IOException {
    String expectedResponse = objectMapper.writeValueAsString(inputObject);

    ResponseEntity<String> actualResponse = restTemplate.postForEntity(baseUrl, inputObject, String.class);

    assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    JSONAssert.assertEquals(expectedResponse, Objects.requireNonNull(actualResponse.getBody()),
        new CustomComparator(JSONCompareMode.LENIENT, singleCustomAssert));

    E actualSavedObject = objectMapper.readValue(actualResponse.getBody(), clazz);
    BeanUtils.copyProperties(actualSavedObject, inputObject);
  }

  public void assertListResultWithQuickSearch(String quickSearch, E... e)
      throws JsonProcessingException, JSONException {
    assertListResultWithQuickSearch(quickSearch, collectionCustomAssert, e);

  }
  public void assertListResultWithQuickSearch(String quickSearch, Customization[] customizations, E... e)
      throws JsonProcessingException, JSONException {
    Page<E> expectedData = new PageImpl<>(Arrays.asList(e), PageRequest.of(0, 20), e.length);
    String expectedResponse = objectMapper.writeValueAsString(expectedData);

    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl)
        .queryParam("filter", quickSearch);

    ResponseEntity<String> actualResponse = restTemplate.getForEntity(builder.build().encode().toString(), String.class);

    JSONAssert.assertEquals(expectedResponse, Objects.requireNonNull(actualResponse.getBody()),
        new CustomComparator(JSONCompareMode.LENIENT, customizations));
  }

}
