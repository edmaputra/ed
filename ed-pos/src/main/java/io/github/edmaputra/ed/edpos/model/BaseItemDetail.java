package io.github.edmaputra.ed.edpos.model;

import io.github.edmaputra.ed.edbase.model.BaseIdEntity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class BaseItemDetail<ID extends Serializable> extends BaseIdEntity<ID> {

  private BigDecimal sellPrice;

  private BigDecimal costPrice;

  private int stock;

  private int stockAlert;

  private ZonedDateTime expiryDate;

  private String variant;

  private String sku;

  private String barcode;

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

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getBarcode() {
    return barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }
}
