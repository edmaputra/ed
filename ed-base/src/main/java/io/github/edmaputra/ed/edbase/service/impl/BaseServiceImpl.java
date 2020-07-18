package io.github.edmaputra.ed.edbase.service.impl;

import io.github.edmaputra.ed.edbase.exception.CrudOperationException;
import io.github.edmaputra.ed.edbase.exception.DataEmptyException;
import io.github.edmaputra.ed.edbase.exception.DataNotFoundException;
import io.github.edmaputra.ed.edbase.model.BaseIdEntity;
import io.github.edmaputra.ed.edbase.predicate.BasePredicate;
import io.github.edmaputra.ed.edbase.repository.BaseRepository;
import io.github.edmaputra.ed.edbase.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * {@inheritDoc}
 */
public class BaseServiceImpl<T extends BaseIdEntity<ID>, ID extends Serializable>
    implements BaseService<T, ID> {

  private static final String ENTITY_NULL_MESSAGES = "Entity that want to delete is Null";
  private static final String IGNORED_PROPERTIES = "creator, createTime";
  private final BaseRepository<T, ID> repository;
  private final BasePredicate<T> predicate;

  public BaseServiceImpl(BaseRepository<T, ID> repository, BasePredicate<T> predicate) {
    this.repository = repository;
    this.predicate = predicate;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Page<T> get(Pageable pageable) throws DataEmptyException, CrudOperationException {
    if (pageable == null) {
      throw new CrudOperationException("Pagination is Null");
    }
    Page<T> tPage = repository.findAll(pageable);
    if (tPage.isEmpty()) {
      throw new DataEmptyException("Data Empty");
    }
    return tPage;
  }

  @Override
  public Page<T> get(Pageable pageable, String keyword) throws DataEmptyException, CrudOperationException {
    if (pageable == null) {
      throw new CrudOperationException("Pagination is Null");
    }
    Page<T> tPage = repository.findAll(predicate.getPredicate(keyword), pageable);
    if (tPage.isEmpty()) {
      throw new DataEmptyException("Data Empty");
    }
    return tPage;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T getOne(ID id) throws DataNotFoundException, CrudOperationException {
    if (id == null) {
      throw new CrudOperationException("ID is Null");
    }
    return repository.findById(id).orElseThrow(DataNotFoundException::new);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T add(T t) throws CrudOperationException {
    validate(t);
    return (T) repository.save(t);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T update(T t) throws DataNotFoundException, CrudOperationException {
    validate(t);
    T entity = getOne(t.getId());
    BeanUtils.copyProperties(t, entity, IGNORED_PROPERTIES);
    repository.save(entity);
    return entity;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T delete(T t) throws DataNotFoundException, CrudOperationException {
    validate(t);
    T entity = getOne(t.getId());
    entity.setDeleteFlag(true);
    entity = (T) repository.save(entity);
    return entity;
  }

  @Override
  public T hardDelete(T t) throws DataNotFoundException, CrudOperationException {
    validate(t);
    T saved = getOne(t.getId());
    repository.delete(saved);
    return saved;
  }

  private void validate(T t) throws CrudOperationException {
    if (t == null) {
      throw new CrudOperationException(ENTITY_NULL_MESSAGES);
    }
  }
}
