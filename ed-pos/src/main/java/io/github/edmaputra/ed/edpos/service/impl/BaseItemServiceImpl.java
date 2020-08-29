package io.github.edmaputra.ed.edpos.service.impl;

import io.github.edmaputra.ed.edbase.exception.CrudOperationException;
import io.github.edmaputra.ed.edbase.exception.DataNotFoundException;
import io.github.edmaputra.ed.edbase.predicate.BasePredicate;
import io.github.edmaputra.ed.edbase.service.impl.BaseServiceImpl;
import io.github.edmaputra.ed.edpos.model.BaseCategory;
import io.github.edmaputra.ed.edpos.model.BaseItem;
import io.github.edmaputra.ed.edpos.repository.BaseItemRepository;
import io.github.edmaputra.ed.edpos.service.BaseCategoryService;
import io.github.edmaputra.ed.edpos.service.BaseItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public abstract class BaseItemServiceImpl<T extends BaseItem<C, ID>, C extends BaseCategory<ID>, ID extends Serializable>
    extends BaseServiceImpl<T, ID>
    implements BaseItemService<T, C, ID> {

  private final BaseItemRepository<T, C, ID> repository;
  private final BaseCategoryService<C, ID> categoryService;

  public BaseItemServiceImpl(BaseItemRepository<T, C, ID> repository, BasePredicate<T> predicate,
                             BaseCategoryService<C, ID> categoryService) {
    super(repository, predicate);
    this.repository = repository;
    this.categoryService = categoryService;
  }

  @Override
  public Page<T> findByCategory(ID categoryId, Pageable pageable) throws CrudOperationException, DataNotFoundException {
    C category = categoryService.getOne(categoryId);
    Page<T> result = repository.findByCategory(category, pageable);
    return result;
  }

  @Override
  public T add(T t) throws DataNotFoundException, CrudOperationException {
    findAndSetCategory(t);
    return super.add(t);
  }

  @Override
  public T update(T t) throws DataNotFoundException, CrudOperationException {
    findAndSetCategory(t);
    return super.update(t);
  }

  private void findAndSetCategory(T t) throws CrudOperationException, DataNotFoundException {
    C c = categoryService.getOne(t.getCategory().getId());
    t.setCategory(c);
  }
}
