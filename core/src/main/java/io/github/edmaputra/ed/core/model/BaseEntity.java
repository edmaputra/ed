package io.github.edmaputra.ed.core.model;

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
import java.time.Instant;

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

  protected boolean deleteFlag;

  public BaseEntity() {
    this("1", Instant.now(), "", Instant.now(), "", false);
  }

  public BaseEntity(String version, Instant createTime, String creator, Instant updateTime, String updater, boolean deleteFlag) {
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

  public boolean isDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(boolean deleteFlag) {
    this.deleteFlag = deleteFlag;
  }
}
