package io.github.edmaputra.ed.edpos.service;

import io.github.edmaputra.ed.edbase.exception.CrudOperationException;
import io.github.edmaputra.ed.edbase.exception.DataNotFoundException;
import io.github.edmaputra.ed.edbase.service.BaseService;
import io.github.edmaputra.ed.edpos.model.BaseCategory;
import io.github.edmaputra.ed.edpos.model.BaseItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface BaseItemService<T extends BaseItem<C, ID>, C extends BaseCategory<?>, ID extends Serializable>
    extends BaseService<T, ID> {

  Page<T> findByCategory(ID categoryId, Pageable pageable) throws CrudOperationException, DataNotFoundException;

}
