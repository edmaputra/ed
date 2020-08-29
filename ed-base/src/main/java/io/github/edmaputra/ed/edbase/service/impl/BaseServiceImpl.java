package io.github.edmaputra.ed.edbase.service.impl;

import io.github.edmaputra.ed.edbase.exception.CrudOperationException;
import io.github.edmaputra.ed.edbase.exception.DataNotFoundException;
import io.github.edmaputra.ed.edbase.model.BaseIdEntity;
import io.github.edmaputra.ed.edbase.predicate.BasePredicate;
import io.github.edmaputra.ed.edbase.repository.BaseRepository;
import io.github.edmaputra.ed.edbase.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * {@inheritDoc}
 */
public class BaseServiceImpl<T extends BaseIdEntity<ID>, ID extends Serializable>
    implements BaseService<T, ID> {

  private static final String ENTITY_NULL_MESSAGES = "Entity that want to delete is Null";
  private static final String ENTITY_NOT_FOUND_MESSAGES = " not found";
  private static final String[] IGNORED_PROPERTIES = new String[]{"creator", "createTime"};
  private final BaseRepository<T, ID> repository;
  private final BasePredicate<T> predicate;
  private final Class<?> entityClass;

  private AuditorAware<String> auditorAware;

  public BaseServiceImpl(BaseRepository<T, ID> repository, BasePredicate<T> predicate) {
    this.repository = repository;
    this.predicate = predicate;
    this.entityClass = Objects.requireNonNull(GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseService.class))[0];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Page<T> get(Pageable pageable) throws CrudOperationException {
    return get(pageable, "");
  }

  @Override
  public Page<T> get(Pageable pageable, String keyword) throws CrudOperationException {
    if (pageable == null) {
      throw new CrudOperationException("Pagination is Null");
    }
    return repository.findAll(predicate.getPredicate(keyword), pageable);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T getOne(ID id) throws DataNotFoundException, CrudOperationException {
    if (id == null) {
      throw new CrudOperationException("ID is Null");
    }
    return repository.findById(id).orElseThrow(
        () -> new DataNotFoundException(this.entityClass.getSimpleName() + ENTITY_NOT_FOUND_MESSAGES));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T add(T t) throws DataNotFoundException, CrudOperationException {
    validate(t);
    return repository.save(t);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T update(T t) throws DataNotFoundException, CrudOperationException {
    validate(t);
    T entity = getOne(t.getId());
    BeanUtils.copyProperties(t, entity, IGNORED_PROPERTIES);
    return repository.save(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T delete(T t) throws DataNotFoundException, CrudOperationException {
    validate(t);
    T entity = getOne(t.getId());
    entity.setDeleteFlag(true);
    entity.setDeleteBy(auditor());
    entity.setDeleteTime(ZonedDateTime.now());
    return repository.save(entity);
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

  private String auditor() {
    return auditorAware.getCurrentAuditor().orElse("anonymous");
  }

  @Qualifier("edAuditorAware")
  @Autowired
  public void setAuditorAware(AuditorAware<String> auditorAware) {
    this.auditorAware = auditorAware;
  }
}
