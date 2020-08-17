package io.github.edmaputra.ed.sample.model;

import io.github.edmaputra.ed.edpos.model.BaseCategory;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Category extends BaseCategory<UUID, Item> {
}
