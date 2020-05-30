package io.github.edmaputra.ed.core.model;

import io.github.edmaputra.ed.core.annotation.FilterType;
import io.github.edmaputra.ed.core.annotation.Filterable;
import io.github.edmaputra.ed.core.constant.Contract;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BaseEntityTest {

  private BaseEntity baseEntity, baseEntityWithData, filterableEntity;

  private static final String DEFAULT_VERSION = "1";
  private static final String DEFAULT_CREATOR = "";
  private static final String DEFAULT_UPDATER = "";
  private static final boolean DEFAULT_RECORDED = true;

  private static final Instant CREATION_TIME = Instant.ofEpochMilli(1586275079);
  private static final Instant UPDATE_TIME = Instant.ofEpochMilli(1586275079);

  @BeforeEach
  void init() {
    baseEntity = new BaseEntityImpl();
    baseEntityWithData = new BaseEntityImpl(
        "1.1",
        CREATION_TIME,
        "administrator",
        UPDATE_TIME,
        "administrator",
        Boolean.TRUE
    );

    filterableEntity = new FilterableEntity("String", 100, 10000);
  }

  @Test
  void givenBaseEntity_whenGetterInvoked_thenShouldReturnDefaultValue() {
    assertThat(baseEntity.getVersion()).isEqualTo(DEFAULT_VERSION);
    assertThat(baseEntity.getCreator()).isEqualTo(DEFAULT_CREATOR);
    assertThat(baseEntity.getUpdater()).isEqualTo(DEFAULT_UPDATER);
    assertThat(baseEntity.isRecorded()).isEqualTo(DEFAULT_RECORDED);

    assertThat(baseEntity.getCreateTime()).isNotNull();
    assertThat(baseEntity.getUpdateTime()).isNotNull();
  }

  @Test
  void givenBaseEntityWithData_whenGetterInvoked_thenShouldReturnExpectedValue() {
    assertThat(baseEntityWithData.getVersion()).isEqualTo("1.1");
    assertThat(baseEntityWithData.getCreator()).isEqualTo("administrator");
    assertThat(baseEntityWithData.getUpdater()).isEqualTo("administrator");
    assertThat(baseEntityWithData.isRecorded()).isEqualTo(true);

    assertThat(baseEntityWithData.getCreateTime()).isEqualTo(CREATION_TIME);
    assertThat(baseEntityWithData.getUpdateTime()).isEqualTo(UPDATE_TIME);
  }

  @Test
  void givenBaseEntity_whenSetterInvoked_thenShouldReturnExpectedValue() {
    Instant time = Instant.ofEpochMilli(1586275448);
    baseEntity.setVersion("2");
    baseEntity.setCreateTime(time);
    baseEntity.setUpdateTime(time);
    baseEntity.setCreator("USER");
    baseEntity.setUpdater("USER");
    baseEntity.setRecorded(false);

    assertThat(baseEntity.getVersion()).isEqualTo("2");
    assertThat(baseEntity.getCreator()).isEqualTo("USER");
    assertThat(baseEntity.getUpdater()).isEqualTo("USER");
    assertThat(baseEntity.isRecorded()).isEqualTo(false);

    assertThat(baseEntity.getCreateTime()).isEqualTo(time);
    assertThat(baseEntity.getUpdateTime()).isEqualTo(time);
  }

  @Test
  void givenFilterableEntity_whenGetFilterableFields_thenReturnCorrectFields() {
    Map<String, Object> field1 = new HashMap<>();
    field1.put(Contract.FILTERABLE_FIELD_MAP_KEY, "field1");
    field1.put(Contract.FILTERABLE_TYPE_MAP_KEY, FilterType.STRING);

    List<Map> annotatedFields = filterableEntity.getFilterableFields();

    assertThat(annotatedFields.size()).isEqualTo(2);
    assertThat(annotatedFields).contains(field1);
    assertThat(annotatedFields.get(0).containsValue(FilterType.STRING)).isTrue();
    assertThat(annotatedFields.get(0).containsValue("field1")).isTrue();
    assertThat(annotatedFields.get(1).containsValue(FilterType.NUMBER)).isTrue();
    assertThat(annotatedFields.get(1).containsValue("field2")).isTrue();
  }

  @Test
  void givenObjectWithoutFilterableField_whenGetFilterableFields_thenShouldReturnEmptyList() {
    List<Map> annotatedFields = baseEntityWithData.getFilterableFields();

    assertThat(annotatedFields.isEmpty()).isTrue();
    assertThat(annotatedFields.size()).isZero();
  }

  private static class BaseEntityImpl extends BaseEntity {

    public BaseEntityImpl() {
      super();
    }

    public BaseEntityImpl(String version, Instant createdOn, String creator, Instant updatedOn, String updater, boolean recorded) {
      super(version, createdOn, creator, updatedOn, updater, recorded);
    }
  }

  private static class FilterableEntity extends BaseEntity {

    @Filterable
    private String field1;

    @Filterable(type = FilterType.NUMBER)
    private int field2;

    private double field3;

    public FilterableEntity(String string, int num, double aDouble) {
      this.field1 = string;
      this.field2 = num;
      this.field3 = aDouble;
    }
  }

}
