package io.github.edmaputra.ed.core.model;

import io.github.edmaputra.ed.core.annotation.Filterable;
import io.github.edmaputra.ed.core.constant.Contract;
import io.github.edmaputra.ed.core.constant.DbColumn;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base Entity class in abstract form
 *
 * @author edmaputra
 * @since 0.0.1
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @NotBlank(message = "Version should not be blank")
  @Size(max = DbColumn.VERSION_LENGTH, min = 1, message = "Version length should be 1 - " + DbColumn.VERSION_LENGTH)
  @Column(length = DbColumn.VERSION_LENGTH)
  protected String version;

  @CreatedDate
  @PastOrPresent(message = "Create Time should be past or present")
  protected Instant createTime;

  @CreatedBy
  @Column(length = DbColumn.NAME_LENGTH)
  protected String creator;

  @LastModifiedDate
  @PastOrPresent(message = "Create Time should be past or present")
  protected Instant updateTime;

  @LastModifiedBy
  @Column(length = DbColumn.NAME_LENGTH)
  protected String updater;

  protected boolean recorded;

  public BaseEntity() {
    this("1", Instant.now(), "", Instant.now(), "", true);
  }

  public BaseEntity(String version, Instant createTime, String creator, Instant updateTime, String updater, boolean recorded) {
    this.version = version;
    this.createTime = createTime;
    this.creator = creator;
    this.updateTime = updateTime;
    this.updater = updater;
    this.recorded = recorded;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Instant getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Instant createTime) {
    this.createTime = createTime;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public Instant getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Instant updateTime) {
    this.updateTime = updateTime;
  }

  public String getUpdater() {
    return updater;
  }

  public void setUpdater(String updater) {
    this.updater = updater;
  }

  public boolean isRecorded() {
    return recorded;
  }

  public void setRecorded(boolean recorded) {
    this.recorded = recorded;
  }

  public List<Map> getFilterableFields() {
    List<Map> list = new ArrayList();
    for (Field field : this.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(Filterable.class)) {
        Filterable filterable = field.getAnnotation(Filterable.class);
        Map<String, Object> map = new HashMap<>();
        map.put(Contract.FILTERABLE_FIELD_MAP_KEY, field.getName());
        map.put(Contract.FILTERABLE_TYPE_MAP_KEY, filterable.type());
        list.add(map);
      }
    }
    return list;
  }
}
