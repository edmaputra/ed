package io.github.edmaputra.ed.edbase.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import io.github.edmaputra.ed.edbase.annotation.FilterModel;
import io.github.edmaputra.ed.edbase.annotation.Filterable;
import io.github.edmaputra.ed.edbase.model.BaseEntity;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BasePredicateImplTest {

  BasePredicateImpl<ItemStore> predicate;
  ItemStore itemStore;

  @BeforeEach
  void init() {
    predicate = new ItemStorePredicate();
    itemStore = new ItemStore("item1", 100, 1000.0);
  }

  @Test
  void whenGetEntityName_shouldReturnCorrectValue() {
    assertThat(predicate.getGenericClassEntityName()).isEqualTo("itemStore");
  }

  @Test
  void whenGetFilterableFields_thenShouldReturnCorrectList() {
    List<FilterModel> filterableFields = predicate.getGenericClassFilterableFields();

    assertThat(filterableFields.size()).isEqualTo(3);
  }

  @Test
  void givenKeyword_whenGetPredicate_thenReturnCorrectValue() {
    String keyword1 = "123";
    BooleanExpression actual1 = predicate.getPredicate(keyword1);
    assertThat(actual1).isEqualTo(expectedExpression(keyword1));

    String keyword2 = "123qwe";
    BooleanExpression actual2 = predicate.getPredicate(keyword2);
    assertThat(actual2).isEqualTo(expectedExpression(keyword2));

    String keyword3 = "123.18";
    BooleanExpression actual3 = predicate.getPredicate(keyword3);
    assertThat(actual3).isEqualTo(expectedExpression(keyword3));
  }

  private BooleanExpression expectedExpression(String keyword) {
    PathBuilder<ItemStore> path = new PathBuilder<>(ItemStore.class, "itemStore");
    BooleanExpression expression = Expressions.asBoolean(true).isFalse()
        .or(path.getString("name").containsIgnoreCase(keyword));
    if (NumberUtils.isParsable(keyword)) {
      NumberPath numberPath = path.getNumber("stock", Integer.class);
      expression = expression.or(numberPath.like("%" + keyword + "%"));

      NumberPath doublePath = path.getNumber("price", Double.class);
      expression = expression.or(doublePath.like("%" + keyword + "%"));
    }
    return expression;
  }

  class ItemStorePredicate extends BasePredicateImpl<ItemStore> {
  }

  class ItemStore extends BaseEntity {

    @Filterable
    private String name;

    @Filterable
    private int stock;

    @Filterable
    private double price;

    public ItemStore(String name, int stock, double price) {
      this.name = name;
      this.stock = stock;
      this.price = price;
    }
  }

}




