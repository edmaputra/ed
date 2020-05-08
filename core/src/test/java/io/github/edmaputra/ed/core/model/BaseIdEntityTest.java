package io.github.edmaputra.ed.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BaseIdEntityTest {

  private BaseIdEntity impl;

  private final static UUID ID = UUID.randomUUID();
  private static final String DEFAULT_VERSION = "1";
  private static final String DEFAULT_CREATOR = "";
  private static final String DEFAULT_UPDATER = "";
  private static final boolean DEFAULT_RECORDED = true;

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
  @Disabled
  void givenBaseIdEntityImpl_whenIntegrateWithBaseEntity_thenShouldReturnExpectedValue() {
    Instant time = Instant.ofEpochMilli(1586445970);
    impl = new BaseIdEntityImpl();

    assertThat(impl.getId()).isNotNull();
    assertThat(impl.getVersion()).isEqualTo("1");
    assertThat(impl.getCreator()).isEqualTo("");
    assertThat(impl.getUpdater()).isEqualTo("");
    assertThat(impl.isRecorded()).isEqualTo(true);

    assertThat(impl.getCreateTime()).isEqualTo(time);
    assertThat(impl.getUpdateTime()).isEqualTo(time);


  }

  private void assertDefaultValue(BaseIdEntity<UUID> entity) {
    assertThat(entity.getVersion()).isEqualTo(DEFAULT_VERSION);
    assertThat(entity.getCreator()).isEqualTo(DEFAULT_CREATOR);
    assertThat(entity.getUpdater()).isEqualTo(DEFAULT_UPDATER);
    assertThat(entity.isRecorded()).isEqualTo(DEFAULT_RECORDED);

    assertThat(entity.getCreateTime()).isNotNull();
    assertThat(entity.getUpdateTime()).isNotNull();
  }


  private static class BaseIdEntityImpl extends BaseIdEntity<UUID> {

    public BaseIdEntityImpl() {
      setId(UUID.randomUUID());
    }
  }

}
