package io.github.edmaputra.ed.edpos.model;

import io.github.edmaputra.ed.edbase.model.BasePerson;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEmployee<ID extends Serializable> extends BasePerson<ID> {

}
