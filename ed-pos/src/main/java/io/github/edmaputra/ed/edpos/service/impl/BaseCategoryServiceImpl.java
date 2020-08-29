package io.github.edmaputra.ed.edpos.service.impl;

import io.github.edmaputra.ed.edbase.predicate.BasePredicate;
import io.github.edmaputra.ed.edbase.service.impl.BaseServiceImpl;
import io.github.edmaputra.ed.edpos.model.BaseCategory;
import io.github.edmaputra.ed.edpos.repository.BaseCategoryRepository;
import io.github.edmaputra.ed.edpos.service.BaseCategoryService;

import java.io.Serializable;

public abstract class BaseCategoryServiceImpl<T extends BaseCategory<ID>, ID extends Serializable>
    extends BaseServiceImpl<T, ID>
    implements BaseCategoryService<T, ID> {

  public BaseCategoryServiceImpl(BaseCategoryRepository<T, ID> repository, BasePredicate<T> predicate) {
    super(repository, predicate);
  }

}
