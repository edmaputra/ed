package io.github.edmaputra.ed.data.repository;

import io.github.edmaputra.ed.core.model.BaseEntity;
import io.github.edmaputra.ed.core.model.BaseIdEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * Base repository interface that extends {@link JpaRepository}
 * * and use entity from {@link BaseEntity}
 *
 * @param <T>  type of {@link BaseEntity}
 * @param <ID> type of Id
 * @author edmaputra
 * @since 0.0.1
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseIdEntity, ID> extends
    JpaRepository<T, ID>,
    QuerydslPredicateExecutor<T> {

  /**
   * Returns a {@link Optional} of entities with paging restriction provided in the {@code Pageable} object
   * with recorded field as TRUE
   *
   * @param pageable pageable values
   * @return a page of entities
   */
  Optional<Page<T>> findByDeleteFlagFalse(Pageable pageable);

  /**
   * Returns a {@link Optional} of entities with paging restriction provided in the {@code Pageable} object
   * and deleteFlag parameter in boolean type
   *
   * @param deleteFlag is deleted or not
   * @param pageable pageable entity
   * @return a page of entities
   */
  Optional<Page<T>> findByDeleteFlag(boolean deleteFlag, Pageable pageable);

}
