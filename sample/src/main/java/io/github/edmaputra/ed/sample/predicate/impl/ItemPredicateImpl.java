package io.github.edmaputra.ed.sample.predicate.impl;

import io.github.edmaputra.ed.edbase.predicate.impl.BasePredicateImpl;
import io.github.edmaputra.ed.sample.model.Item;
import io.github.edmaputra.ed.sample.predicate.ItemPredicate;
import org.springframework.stereotype.Component;

@Component
public class ItemPredicateImpl extends BasePredicateImpl<Item> implements ItemPredicate {
}
