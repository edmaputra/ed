package io.github.edmaputra.ed.edbase.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import io.github.edmaputra.ed.edbase.model.BaseEntity;

public interface BasePredicate<T extends BaseEntity> {

  BooleanExpression getPredicate(String keyword);

}
