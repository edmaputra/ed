package io.github.edmaputra.ed.web.controller;

import io.github.edmaputra.ed.core.exception.CrudOperationException;
import io.github.edmaputra.ed.core.exception.DataEmptyException;
import io.github.edmaputra.ed.core.exception.DataNotFoundException;
import io.github.edmaputra.ed.core.model.BaseIdEntity;
import io.github.edmaputra.ed.data.service.BaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


/**
 * Base Controller for Presentation Layer.
 *
 * @param <T>  the domain which extends {@link BaseIdEntity}
 * @param <ID> the type of the id of the entity
 * @author edmaputra
 * @since 0.0.1
 */
public class BaseController<T extends BaseIdEntity, ID> {

  private final BaseService<T, ID> service;

  public BaseController(BaseService<T, ID> service) {
    this.service = service;
  }

  /**
   * Get all entity with pagination
   *
   * @param pageable pagination
   * @return Response Entity with result body
   * @throws CrudOperationException when crud operation error
   * @throws DataEmptyException     when data is empty
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity get(Pageable pageable) throws CrudOperationException, DataEmptyException {
    return ResponseEntity.ok().body(service.get(pageable));
  }

  /**
   * Get entity by ID
   *
   * @param id the id of entity
   * @return the entity
   * @throws CrudOperationException when passed bad parameter/id
   * @throws DataNotFoundException  when data is not found
   */
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity find(@PathVariable ID id) throws CrudOperationException, DataNotFoundException {
    T t = service.getOne(id);
    return ResponseEntity.ok().body(t);
  }

  /**
   * Add new entity
   *
   * @param t the entity
   * @return the entity that saved
   * @throws CrudOperationException when something bad happen
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity add(@Valid @RequestBody T t) throws CrudOperationException {
    T result = service.add(t);
    return new ResponseEntity<>(result, HttpStatus.CREATED);
  }

  /**
   * Update the entity with new value
   *
   * @param t the entity with latest value
   * @return the entity that have updated
   * @throws CrudOperationException when entity want to update have bad values
   * @throws DataNotFoundException  when entity want to update is not found
   */
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<T> update(@Valid @RequestBody T t)
      throws CrudOperationException, DataNotFoundException {

    T result = service.update(t);
    return ResponseEntity.ok().body(result);
  }


  /**
   * Delete an entity
   *
   * @param t the entity that want to delete
   * @return the entity that have deleted
   * @throws DataNotFoundException  when entity is not found
   * @throws CrudOperationException when entity have bad values
   */
  @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity delete(@Valid @RequestBody T t) throws CrudOperationException, DataNotFoundException {
    T result = service.delete(t);
    return ResponseEntity.ok().body(result);
  }
}
