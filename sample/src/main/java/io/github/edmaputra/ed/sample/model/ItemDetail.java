package io.github.edmaputra.ed.sample.model;

import io.github.edmaputra.ed.edpos.model.BaseItemDetail;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class ItemDetail extends BaseItemDetail<Item, UUID> {
}
