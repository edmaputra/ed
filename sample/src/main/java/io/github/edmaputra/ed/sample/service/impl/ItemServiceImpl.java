package io.github.edmaputra.ed.sample.service.impl;

import io.github.edmaputra.ed.edpos.service.impl.BaseItemServiceImpl;
import io.github.edmaputra.ed.sample.model.Category;
import io.github.edmaputra.ed.sample.model.Item;
import io.github.edmaputra.ed.sample.predicate.ItemPredicate;
import io.github.edmaputra.ed.sample.repository.ItemRepository;
import io.github.edmaputra.ed.sample.service.CategoryService;
import io.github.edmaputra.ed.sample.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemServiceImpl extends BaseItemServiceImpl<Item, Category, UUID> implements ItemService {

  public ItemServiceImpl(ItemRepository repository, ItemPredicate predicate, CategoryService categoryService) {
    super(repository, predicate, categoryService);
  }
}
