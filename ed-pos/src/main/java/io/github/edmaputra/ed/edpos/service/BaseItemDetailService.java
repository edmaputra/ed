package io.github.edmaputra.ed.edpos.service;

import io.github.edmaputra.ed.edbase.exception.CrudOperationException;
import io.github.edmaputra.ed.edbase.exception.DataNotFoundException;
import io.github.edmaputra.ed.edbase.service.BaseService;
import io.github.edmaputra.ed.edpos.model.BaseItem;
import io.github.edmaputra.ed.edpos.model.BaseItemDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface BaseItemDetailService<T extends BaseItemDetail<C, ID>, C extends BaseItem<?, ?>, ID extends Serializable>
    extends BaseService<T, ID> {

  Page<T> findByItem(ID itemId, Pageable pageable) throws CrudOperationException, DataNotFoundException;

}
