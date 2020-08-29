package io.github.edmaputra.ed.edpos.controller;

import io.github.edmaputra.ed.edbase.controller.BaseController;
import io.github.edmaputra.ed.edbase.exception.CrudOperationException;
import io.github.edmaputra.ed.edbase.exception.DataNotFoundException;
import io.github.edmaputra.ed.edpos.model.BaseItemDetail;
import io.github.edmaputra.ed.edpos.service.BaseItemDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

public abstract class BaseItemDetailController<T extends BaseItemDetail<?, ID>, ID extends Serializable>
    extends BaseController<T, ID> {

  private final BaseItemDetailService<T, ?, ID> service;

  public BaseItemDetailController(BaseItemDetailService<T, ?, ID> service) {
    super(service);
    this.service = service;
  }

  @GetMapping(value = "/byItem", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<T>> getByItem(@RequestParam("id") ID id,
                                           Pageable pageable) throws CrudOperationException, DataNotFoundException {
    return ResponseEntity.ok().body(service.findByItem(id, pageable));
  }
}
