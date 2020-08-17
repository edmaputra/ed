package io.github.edmaputra.ed.sample.model;

import io.github.edmaputra.ed.edbase.model.BaseAddress;
import io.github.edmaputra.ed.edbase.model.BaseIdEntity;

import javax.persistence.Entity;

@Entity
public class Address extends BaseIdEntity<Long> {

  private BaseAddress address;
}
