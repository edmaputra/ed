package io.github.edmaputra.ed.edpos.repository;

import io.github.edmaputra.ed.edbase.repository.BaseRepository;
import io.github.edmaputra.ed.edpos.model.BaseCategory;
import io.github.edmaputra.ed.edpos.model.BaseItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseItemRepository<T extends BaseItem<C, ID>, C extends BaseCategory<?>, ID extends Serializable>
    extends BaseRepository<T, ID> {

  Page<T> findByCategory(C category, Pageable pageable);
}
