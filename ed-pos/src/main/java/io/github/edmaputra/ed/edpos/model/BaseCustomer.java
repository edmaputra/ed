package io.github.edmaputra.ed.edpos.model;


import io.github.edmaputra.ed.edbase.model.BaseAddress;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseCustomer<ID extends Serializable> extends BaseSubject<ID> {
}
