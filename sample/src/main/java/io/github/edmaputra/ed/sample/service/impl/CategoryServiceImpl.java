package io.github.edmaputra.ed.sample.service.impl;

import io.github.edmaputra.ed.edbase.service.impl.BaseServiceImpl;
import io.github.edmaputra.ed.sample.model.Category;
import io.github.edmaputra.ed.sample.predicate.CategoryPredicate;
import io.github.edmaputra.ed.sample.repository.CategoryRepository;
import io.github.edmaputra.ed.sample.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, UUID> implements CategoryService {

  public CategoryServiceImpl(CategoryRepository repository, CategoryPredicate predicate) {
    super(repository, predicate);
  }
}