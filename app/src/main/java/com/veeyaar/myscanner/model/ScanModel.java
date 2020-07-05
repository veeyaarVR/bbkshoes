package com.veeyaar.myscanner.model;

import java.io.Serializable;

public class ScanModel implements Serializable {

  private String quantity;
  private String locationCode;
  private String productCode;

  public ScanModel(String quantity, String locationCode, String productCode) {
    this.quantity = quantity;
    this.locationCode = locationCode;
    this.productCode = productCode;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getLocationCode() {
    return locationCode;
  }

  public void setLocationCode(String locationCode) {
    this.locationCode = locationCode;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }
}
