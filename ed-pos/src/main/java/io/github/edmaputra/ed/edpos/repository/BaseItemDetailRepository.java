package io.github.edmaputra.ed.edpos.repository;

import io.github.edmaputra.ed.edbase.repository.BaseRepository;
import io.github.edmaputra.ed.edpos.model.BaseItem;
import io.github.edmaputra.ed.edpos.model.BaseItemDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseItemDetailRepository<T extends BaseItemDetail<I, ID>, I extends BaseItem<?, ?>, ID extends Serializable>
    extends BaseRepository<T, ID> {

  Page<T> findByItem(I item, Pageable pageable);

}
