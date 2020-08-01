package io.github.edmaputra.ed.edbase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.edmaputra.ed.edbase.constant.Contract;
import io.github.edmaputra.ed.edbase.constant.DbColumn;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Base Entity class in abstract form
 *
 * @author edmaputra
 * @since 0.0.1
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity implements Serializable {

  @NotBlank(message = "Version should not be blank")
  @Size(max = DbColumn.VERSION_LENGTH, min = 1, message = "Version length should be between 1 - " + DbColumn.VERSION_LENGTH)
  @Column(length = DbColumn.VERSION_LENGTH)
  protected String version;

  @CreatedDate
  @PastOrPresent(message = "Create Time should be past or present")
  @JsonFormat(pattern = Contract.ZONED_DATE_TIME_JSON_PATTERN)
  @Column(updatable = false)
  protected ZonedDateTime createTime;

  @CreatedBy
  @Column(length = DbColumn.NAME_LENGTH, updatable = false)
  protected String creator;

  @LastModifiedDate
  @PastOrPresent(message = "Create Time should be past or present")
  @JsonFormat(pattern = Contract.ZONED_DATE_TIME_JSON_PATTERN)
  protected ZonedDateTime updateTime;

  @LastModifiedBy
  @Column(length = DbColumn.NAME_LENGTH)
  protected String updater;

  protected boolean deleteFlag;

  @JsonFormat(pattern = Contract.ZONED_DATE_TIME_JSON_PATTERN)
  protected ZonedDateTime deleteTime;

  @Column(length = DbColumn.NAME_LENGTH)
  protected String deleteBy;

  public BaseEntity() {
    this("1", null, "", null, "", false);
  }

  public BaseEntity(String version, ZonedDateTime createTime, String creator, ZonedDateTime updateTime, String updater, boolean deleteFlag) {
    this.version = version;
    this.createTime = createTime;
    this.creator = creator;
    this.updateTime = updateTime;
    this.updater = updater;
    this.deleteFlag = deleteFlag;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public ZonedDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(ZonedDateTime createTime) {
    this.createTime = createTime;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public ZonedDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(ZonedDateTime updateTime) {
    this.updateTime = updateTime;
  }

  public String getUpdater() {
    return updater;
  }

  public void setUpdater(String updater) {
    this.updater = updater;
  }

  public boolean isDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(boolean deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

  public ZonedDateTime getDeleteTime() {
    return deleteTime;
  }

  public void setDeleteTime(ZonedDateTime deleteTime) {
    this.deleteTime = deleteTime;
  }

  public String getDeleteBy() {
    return deleteBy;
  }

  public void setDeleteBy(String deleteBy) {
    this.deleteBy = deleteBy;
  }
//
//  public <E extends BaseEntity> E creator(String creator) {
//    this.creator = creator;
//    return (E) this;
//  }
//
//  public <E extends BaseEntity> E createTime(ZonedDateTime createTime) {
//    this.createTime = createTime;
//    return (E) this;
//  }
//
//  public <E extends BaseEntity> E updater(String updater) {
//    this.updater = updater;
//    return (E) this;
//  }
//
//  public <E extends BaseEntity> E updateTime(ZonedDateTime updateTime) {
//    this.updateTime = updateTime;
//    return (E) this;
//  }
//
//  public <E extends BaseEntity> E deleteBy(String deleteBy) {
//    this.deleteBy = deleteBy;
//    return (E) this;
//  }
//
//  public <E extends BaseEntity> E deleteTime(ZonedDateTime deleteTime) {
//    this.deleteTime = deleteTime;
//    return (E) this;
//  }
}
