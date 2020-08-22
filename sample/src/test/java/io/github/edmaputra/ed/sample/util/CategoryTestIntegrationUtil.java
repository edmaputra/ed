package io.github.edmaputra.ed.sample.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.edmaputra.ed.sample.model.Category;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class CategoryTestIntegrationUtil extends TestIntegrationUtil<Category> {

  public CategoryTestIntegrationUtil(String baseUrl, TestRestTemplate restTemplate, ObjectMapper objectMapper) {
    super(baseUrl, restTemplate, objectMapper);
  }
}
