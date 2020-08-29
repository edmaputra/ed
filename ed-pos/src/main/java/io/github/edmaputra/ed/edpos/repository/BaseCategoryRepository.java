package io.github.edmaputra.ed.edpos.repository;

import io.github.edmaputra.ed.edbase.repository.BaseRepository;
import io.github.edmaputra.ed.edpos.model.BaseCategory;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseCategoryRepository<T extends BaseCategory<ID>, ID extends Serializable>
    extends BaseRepository<T, ID> {

}
