package io.github.edmaputra.ed.sample.service.impl;

import io.github.edmaputra.ed.edbase.service.impl.BaseServiceImpl;
import io.github.edmaputra.ed.sample.model.Item;
import io.github.edmaputra.ed.sample.predicate.ItemPredicate;
import io.github.edmaputra.ed.sample.repository.ItemRepository;
import io.github.edmaputra.ed.sample.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item, UUID> implements ItemService {

  public ItemServiceImpl(ItemRepository repository, ItemPredicate predicate) {
    super(repository, predicate);
  }
}
