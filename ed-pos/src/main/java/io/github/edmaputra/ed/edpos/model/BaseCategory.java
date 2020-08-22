package io.github.edmaputra.ed.edpos.model;

import io.github.edmaputra.ed.edbase.model.BaseIdAndNameEntity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseCategory<ID extends Serializable> extends BaseIdAndNameEntity<ID> {

}
