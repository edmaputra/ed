package io.github.edmaputra.ed.edpos.model;


import io.github.edmaputra.ed.edbase.model.BaseAddress;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseSupplier<ID extends Serializable> extends BaseSubject<ID> {
}
