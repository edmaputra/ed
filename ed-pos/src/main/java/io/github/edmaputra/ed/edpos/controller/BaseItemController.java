package io.github.edmaputra.ed.edpos.controller;

import io.github.edmaputra.ed.edbase.controller.BaseController;
import io.github.edmaputra.ed.edbase.exception.CrudOperationException;
import io.github.edmaputra.ed.edbase.exception.DataNotFoundException;
import io.github.edmaputra.ed.edpos.model.BaseItem;
import io.github.edmaputra.ed.edpos.service.BaseItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

public abstract class BaseItemController<T extends BaseItem<?, ID>, ID extends Serializable> extends BaseController<T, ID> {

  private final BaseItemService<T, ?, ID> service;

  public BaseItemController(BaseItemService<T, ?, ID> service) {
    super(service);
    this.service = service;
  }

  @GetMapping(value = "/byCategory", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<T>> getByCategory(@RequestParam("id") ID id,
                                               Pageable pageable) throws CrudOperationException, DataNotFoundException {
    return ResponseEntity.ok().body(service.findByCategory(id, pageable));
  }
}
