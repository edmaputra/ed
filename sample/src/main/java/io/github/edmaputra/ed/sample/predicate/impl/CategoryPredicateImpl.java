package io.github.edmaputra.ed.sample.predicate.impl;

import io.github.edmaputra.ed.edbase.predicate.impl.BasePredicateImpl;
import io.github.edmaputra.ed.sample.model.Category;
import io.github.edmaputra.ed.sample.predicate.CategoryPredicate;
import org.springframework.stereotype.Component;

@Component
public class CategoryPredicateImpl extends BasePredicateImpl<Category> implements CategoryPredicate {
}
