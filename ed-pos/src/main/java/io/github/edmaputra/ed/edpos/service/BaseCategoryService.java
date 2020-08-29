package io.github.edmaputra.ed.edpos.service;

import io.github.edmaputra.ed.edbase.service.BaseService;
import io.github.edmaputra.ed.edpos.model.BaseCategory;

import java.io.Serializable;

public interface BaseCategoryService<T extends BaseCategory<ID>, ID extends Serializable> extends BaseService<T, ID> {


}
