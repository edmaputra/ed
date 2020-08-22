package io.github.edmaputra.ed.sample.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edmaputra.ed.sample.model.Item;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class ItemTestIntegrationUtil extends TestIntegrationUtil<Item> {

  public ItemTestIntegrationUtil(String baseUrl, TestRestTemplate restTemplate, ObjectMapper objectMapper) {
    super(baseUrl, restTemplate, objectMapper);
  }
}
