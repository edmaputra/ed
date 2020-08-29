package io.github.edmaputra.ed.edbase.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;

public interface BasePredicate<T> {

  BooleanExpression getPredicate(String keyword);

}
