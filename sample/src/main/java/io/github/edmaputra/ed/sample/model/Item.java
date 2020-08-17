package io.github.edmaputra.ed.sample.model;

import io.github.edmaputra.ed.edpos.model.BaseItem;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Item extends BaseItem<UUID> {
}
