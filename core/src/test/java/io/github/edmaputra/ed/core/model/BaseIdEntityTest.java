package io.github.edmaputra.ed.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BaseIdEntityTest {

  private BaseIdEntity<UUID> impl;

  private final static UUID ID = UUID.randomUUID();
  private static final String DEFAULT_VERSION = "1";
  private static final String DEFAULT_CREATOR = "";
  private static final String DEFAULT_UPDATER = "";
  private static final boolean DEFAULT_DELETE_FLAG = false;

  @BeforeEach
  void init() {
    impl = new BaseIdEntityImpl();
  }

  @Test
  void givenBaseIdEntityImpl_whenInit_thenShouldReturnExpectedValue() {
    assertThat(impl.getId()).isNotNull();

    assertDefaultValue(impl);
  }

  @Test
  void givenBaseIdEntityImpl_whenSetId_thenShouldReturnExpectedValue() {
    impl.setId(ID);

    assertThat(impl.getId()).isEqualTo(ID);
    assertDefaultValue(impl);
  }

  @Test
  void givenBaseIdEntityImpl_whenIntegrateWithBaseEntity_thenShouldReturnExpectedValue() {
    impl = new BaseIdEntityImpl();

    assertThat(impl.getId()).isNotNull();
    assertThat(impl.getVersion()).isEqualTo("1");
    assertThat(impl.getCreator()).isEqualTo("");
    assertThat(impl.getUpdater()).isEqualTo("");
    assertThat(impl.isDeleteFlag()).isEqualTo(false);

    assertThat(impl.getCreateTime()).isNotNull();
    assertThat(impl.getUpdateTime()).isNotNull();
  }

  private void assertDefaultValue(BaseIdEntity<UUID> entity) {
    assertThat(entity.getVersion()).isEqualTo(DEFAULT_VERSION);
    assertThat(entity.getCreator()).isEqualTo(DEFAULT_CREATOR);
    assertThat(entity.getUpdater()).isEqualTo(DEFAULT_UPDATER);
    assertThat(entity.isDeleteFlag()).isEqualTo(DEFAULT_DELETE_FLAG);

    assertThat(entity.getCreateTime()).isNotNull();
    assertThat(entity.getUpdateTime()).isNotNull();
  }


  private static class BaseIdEntityImpl extends BaseIdEntity<UUID> {

    public BaseIdEntityImpl() {
      setId(UUID.randomUUID());
    }
  }

}
