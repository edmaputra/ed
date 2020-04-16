package io.github.edmaputra.ed.data.service;

import io.github.edmaputra.ed.core.model.BaseIdEntity;
import io.github.edmaputra.ed.data.exception.CrudOperationException;
import io.github.edmaputra.ed.data.exception.DataEmptyException;
import io.github.edmaputra.ed.data.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * Base Service Interface for Business Object Layer.
 * Define basic method for all Create, Read, Update and Delete purpose
 *
 * @param <T>  the domain which extends {@link Serializable}
 * @param <ID> the type of the id of the entity
 * @author edmaputra
 * @since 0.0.1
 */
public interface BaseService<T extends BaseIdEntity, ID> {

  /**
   * Get all entities
   *
   * @param pageable pagination param
   * @return Collection of entities
   * @throws DataEmptyException when data is empty or not found
   * @throws CrudOperationException when something bad happen
   */
  Page<T> get(Pageable pageable) throws DataEmptyException, CrudOperationException;

  /**
   * Get an entities by its id
   *
   * @param id id of the entity
   * @return an entities
   * @throws DataNotFoundException when entity with the id not found
   * @throws CrudOperationException when something bad happen
   */
  T getOne(ID id) throws DataNotFoundException, CrudOperationException;

  /**
   * Add new entity;
   *
   * @param t the entity want to add
   * @return the entity that have added
   * @throws CrudOperationException when something bad happen
   */
  T add(T t) throws CrudOperationException;

  /**
   * Update saved entity
   *
   * @param t entity with new value
   * @return entity that have updated
   * @throws DataNotFoundException when entity not found
   * @throws CrudOperationException when something bad happen
   */
  T update(T t) throws DataNotFoundException, CrudOperationException;

  /**
   * Delete an entity
   *
   * @param id the id of the entity
   * @return entity that have deleted
   * @throws DataNotFoundException when entity with given id not found
   * @throws CrudOperationException when something bad happen
   */
  T delete(ID id) throws DataNotFoundException, CrudOperationException;

}
