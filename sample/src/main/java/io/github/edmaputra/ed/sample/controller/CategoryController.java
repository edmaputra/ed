package io.github.edmaputra.ed.sample.controller;

import io.github.edmaputra.ed.edbase.controller.BaseController;
import io.github.edmaputra.ed.sample.model.Category;
import io.github.edmaputra.ed.sample.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController extends BaseController<Category, UUID> {

  public CategoryController(CategoryService service) {
    super(service);
  }
}
