package io.github.edmaputra.ed.data.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import io.github.edmaputra.ed.core.model.BaseEntity;

public interface BasePredicate<T extends BaseEntity> {

  BooleanExpression getPredicate(String keyword);

}
