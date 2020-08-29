package io.github.edmaputra.ed.edpos.model;

import io.github.edmaputra.ed.edbase.model.BaseIdEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class BaseItemDetail<T extends BaseItem<?, ?>, ID extends Serializable> extends BaseIdEntity<ID> {

  @NotNull
  @Min(value = 0)
  private BigDecimal sellPrice;

  @NotNull
  @Min(value = 0)
  private BigDecimal costPrice;

  @NotNull
  @Min(value = 0)
  private int stock;

  @NotNull
  @Min(value = 0)
  private int stockAlert;

  @Future
  private ZonedDateTime expiryDate;

  private String variant;

  private String stockKeepingUnit;

  private String barcode;

  @ManyToOne(optional = false)
  @JoinColumn(name = "item_id")
  @NotNull
  private T item;

  public BigDecimal getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(BigDecimal sellPrice) {
    this.sellPrice = sellPrice;
  }

  public BigDecimal getCostPrice() {
    return costPrice;
  }

  public void setCostPrice(BigDecimal costPrice) {
    this.costPrice = costPrice;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getStockAlert() {
    return stockAlert;
  }

  public void setStockAlert(int stockAlert) {
    this.stockAlert = stockAlert;
  }

  public ZonedDateTime getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(ZonedDateTime expiryDate) {
    this.expiryDate = expiryDate;
  }

  public String getVariant() {
    return variant;
  }

  public void setVariant(String variant) {
    this.variant = variant;
  }

  public String getStockKeepingUnit() {
    return stockKeepingUnit;
  }

  public void setStockKeepingUnit(String stockKeepingUnit) {
    this.stockKeepingUnit = stockKeepingUnit;
  }

  public String getBarcode() {
    return barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }

  public T getItem() {
    return item;
  }

  public void setItem(T item) {
    this.item = item;
  }
}
