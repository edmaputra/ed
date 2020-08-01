package io.github.edmaputra.ed.edbase.model;

import io.github.edmaputra.ed.edbase.annotation.Filterable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class BaseEntityTest {

  private BaseEntity baseEntity, baseEntityWithData, filterableEntity;

  private static final String DEFAULT_VERSION = "1";
  private static final String DEFAULT_CREATOR = "";
  private static final String DEFAULT_UPDATER = "";
  private static final boolean DEFAULT_DELETE_FLAG = false;

  private static final ZonedDateTime CREATION_TIME = ZonedDateTime.now();
  private static final ZonedDateTime UPDATE_TIME = ZonedDateTime.now().plusMinutes(20);

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
    assertThat(baseEntity.isDeleteFlag()).isEqualTo(DEFAULT_DELETE_FLAG);

  }

  @Test
  void givenBaseEntityWithData_whenGetterInvoked_thenShouldReturnExpectedValue() {
    assertThat(baseEntityWithData.getVersion()).isEqualTo("1.1");
    assertThat(baseEntityWithData.getCreator()).isEqualTo("administrator");
    assertThat(baseEntityWithData.getUpdater()).isEqualTo("administrator");
    assertThat(baseEntityWithData.isDeleteFlag()).isEqualTo(true);

    assertThat(baseEntityWithData.getCreateTime()).isEqualTo(CREATION_TIME);
    assertThat(baseEntityWithData.getUpdateTime()).isEqualTo(UPDATE_TIME);
  }

  @Test
  void givenBaseEntity_whenSetterInvoked_thenShouldReturnExpectedValue() {
    ZonedDateTime time = ZonedDateTime.now();
    baseEntity.setVersion("2");
    baseEntity.setCreateTime(time);
    baseEntity.setUpdateTime(time.plusMinutes(20));
    baseEntity.setCreator("USER");
    baseEntity.setUpdater("USER");
    baseEntity.setDeleteFlag(false);

    assertThat(baseEntity.getVersion()).isEqualTo("2");
    assertThat(baseEntity.getCreator()).isEqualTo("USER");
    assertThat(baseEntity.getUpdater()).isEqualTo("USER");
    assertThat(baseEntity.isDeleteFlag()).isEqualTo(false);

    assertThat(baseEntity.getCreateTime()).isEqualTo(time);
    assertThat(baseEntity.getUpdateTime()).isEqualTo(time.plusMinutes(20));
  }

  private static class BaseEntityImpl extends BaseEntity {

    public BaseEntityImpl() {
      super();
    }

    public BaseEntityImpl(String version, ZonedDateTime createdOn, String creator, ZonedDateTime updatedOn, String updater, boolean recorded) {
      super(version, createdOn, creator, updatedOn, updater, recorded);
    }
  }

  private static class FilterableEntity extends BaseEntity {

    @Filterable
    private String field1;

    @Filterable
    private int field2;

    private double field3;

    public FilterableEntity(String string, int num, double aDouble) {
      this.field1 = string;
      this.field2 = num;
      this.field3 = aDouble;
    }
  }

}
