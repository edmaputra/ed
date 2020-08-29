package io.github.edmaputra.ed.edbase.predicate.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import io.github.edmaputra.ed.edbase.annotation.FilterModel;
import io.github.edmaputra.ed.edbase.annotation.Filterable;
import io.github.edmaputra.ed.edbase.predicate.BasePredicate;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BasePredicateImpl<T> implements BasePredicate<T> {

  private final Class<?> clazz;
  private final static Set NUMBER = new HashSet();

  static {
    NUMBER.add(Byte.class);
    NUMBER.add(Short.class);
    NUMBER.add(Integer.class);
    NUMBER.add(Long.class);
    NUMBER.add(Float.class);
    NUMBER.add(Double.class);
  }

  protected BasePredicateImpl() {
    this.clazz = GenericTypeResolver.resolveTypeArguments(this.getClass(), BasePredicate.class)[0];
  }

  public BooleanExpression getPredicate(String keyword) {
    PathBuilder<T> entityPath = new PathBuilder(clazz.getClass(), this.getGenericClassEntityName());

    BooleanExpression result = Expressions.asBoolean(true).isTrue();
    if (!StringUtils.isBlank(keyword)) {
      result = Expressions.asBoolean(true).isFalse();
      for (FilterModel model : this.getGenericClassFilterableFields()) {
        if (model.getType().equals(String.class)) {
          result = result.or(stringExpression(entityPath, model, keyword));
        } else if (NUMBER.contains(model.getType())) {
          if (NumberUtils.isParsable(keyword)) {
            result = result.or(numberExpression(entityPath, model, keyword));
          }
        }
      }
    }
    return result;
  }


  List<FilterModel> getGenericClassFilterableFields() {
    List<FilterModel> list = new ArrayList();
    Class<?> currentClass = this.clazz;
    while (currentClass.getSuperclass() != null) {
      for (Field field : currentClass.getDeclaredFields()) {
        if (field.isAnnotationPresent(Filterable.class)) {
          list.add(new FilterModel(field.getName(), convertPrimitiveToWrapperIfPossible(field.getType())));
        }
      }
      currentClass = currentClass.getSuperclass();
    }
    return list;
  }

  String getGenericClassEntityName() {
    String className = this.clazz.getSimpleName();
    return className.substring(0, 1).toLowerCase().concat(className.substring(1));
  }

  private Class<?> convertPrimitiveToWrapperIfPossible(Class<?> clazzType) {
    Class<?> classType = clazzType;
    if (ClassUtils.isPrimitiveOrWrapper(classType)) {
      classType = ClassUtils.primitiveToWrapper(classType);
    }
    return classType;
  }

  private BooleanExpression stringExpression(PathBuilder entityPath, FilterModel model, String keyword) {
    StringPath path = entityPath.getString(model.getField());
    return path.containsIgnoreCase(keyword);
  }

  private BooleanExpression numberExpression(PathBuilder entityPath, FilterModel model, String keyword) {
    NumberPath numberPath = entityPath.getNumber(model.getField(), model.getType());
    return numberPath.like("%" + keyword + "%");
  }
}
