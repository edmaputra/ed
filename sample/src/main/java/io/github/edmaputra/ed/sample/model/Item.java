package io.github.edmaputra.ed.sample.model;

import io.github.edmaputra.ed.edpos.model.BaseItem;

import javax.persistence.Entity;

@Entity
public class Item extends BaseItem<Long, ItemDetail> {
}
