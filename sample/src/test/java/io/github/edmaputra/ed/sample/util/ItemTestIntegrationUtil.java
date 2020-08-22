package io.github.edmaputra.ed.sample.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edmaputra.ed.sample.model.Item;
import org.skyscreamer.jsonassert.Customization;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static io.github.edmaputra.ed.sample.util.TestUtil.notNull;

public class ItemTestIntegrationUtil extends TestIntegrationUtil<Item> {

  public ItemTestIntegrationUtil(String baseUrl, TestRestTemplate restTemplate, ObjectMapper objectMapper) {
    super(baseUrl, restTemplate, objectMapper, new Customization[]{
        new Customization("id", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("createTime", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("creator", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("updateTime", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("updater", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("category", (actualValue, expectedValue) -> notNull(actualValue))
    }, new Customization[]{
        new Customization("content[*].createTime", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("content[*].creator", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("content[*].updateTime", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("content[*].updater", (actualValue, expectedValue) -> notNull(actualValue)),
        new Customization("content[*].category", (actualValue, expectedValue) -> notNull(actualValue))
    });
  }
}
