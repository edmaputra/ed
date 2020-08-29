package io.github.edmaputra.ed.sample.service;

import io.github.edmaputra.ed.edpos.service.BaseItemService;
import io.github.edmaputra.ed.sample.model.Category;
import io.github.edmaputra.ed.sample.model.Item;

import java.util.UUID;

public interface ItemService extends BaseItemService<Item, Category, UUID> {
}
