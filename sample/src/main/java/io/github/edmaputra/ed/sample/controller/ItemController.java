package io.github.edmaputra.ed.sample.controller;

import io.github.edmaputra.ed.edbase.controller.BaseController;
import io.github.edmaputra.ed.sample.model.Item;
import io.github.edmaputra.ed.sample.service.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController extends BaseController<Item, UUID> {

  public ItemController(ItemService service) {
    super(service);
  }
}
