package io.github.edmaputra.ed.data.service.impl;

import io.github.edmaputra.ed.core.exception.CrudOperationException;
import io.github.edmaputra.ed.core.exception.DataEmptyException;
import io.github.edmaputra.ed.core.exception.DataNotFoundException;
import io.github.edmaputra.ed.core.model.BaseIdEntity;
import io.github.edmaputra.ed.data.predicate.BasePredicate;
import io.github.edmaputra.ed.data.repository.BaseRepository;
import io.github.edmaputra.ed.data.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * {@inheritDoc}
 */
public class BaseServiceImpl<T extends BaseIdEntity, ID> implements BaseService<T, ID> {

  private static final String IGNORED_PROPERTIES = "creator, createTime";
  private final BaseRepository repository;
  private BasePredicate predicate;

  public BaseServiceImpl(BaseRepository repository) {
    this.repository = repository;
//    this.entity =
  }

  public BaseServiceImpl(BaseRepository repository, BasePredicate predicate) {
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
    Optional<T> e = repository.findById(id);
    if (e.isEmpty()) {
      throw new DataNotFoundException("Data Not Found");
    }
    return e.get();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T add(T t) throws CrudOperationException {
    if (t == null) {
      throw new CrudOperationException("Entity that want to create is Null");
    }
    return (T) repository.save(t);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T update(T t) throws DataNotFoundException, CrudOperationException {
    if (t == null) {
      throw new CrudOperationException("Entity that want to update is Null");
    }
    Optional<T> e = repository.findById(t.getId());
    if (!e.isPresent()) {
      throw new DataNotFoundException("Data Not Found");
    }
    T entity = e.get();
    BeanUtils.copyProperties(t, entity, IGNORED_PROPERTIES);
    repository.save(entity);
    return entity;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T delete(T t) throws DataNotFoundException, CrudOperationException {
    if (t == null) {
      throw new CrudOperationException("Entity that want to delete is Null");
    }
    Optional<T> e = repository.findById(t.getId());
    if (!e.isPresent()) {
      throw new DataNotFoundException("Data Not Found");
    }
    T entity = e.get();
    entity.setRecorded(false);
    entity = (T) repository.save(entity);
    return entity;
  }
}
