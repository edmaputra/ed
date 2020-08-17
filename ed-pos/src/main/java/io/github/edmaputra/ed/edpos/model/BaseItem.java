package io.github.edmaputra.ed.edpos.model;

import io.github.edmaputra.ed.edbase.model.BaseIdAndNameEntity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseItem<ID extends Serializable, T extends BaseItemDetail<?>> extends BaseIdAndNameEntity<ID> {

}
