package com.veeyaar.myscanner.model.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "entry")
public class Entry implements Serializable, Comparable<Entry> {

  @PrimaryKey(autoGenerate = true)
  private Integer id;

  @SerializedName("quantity")
  @Expose
  private String quantity;

  @SerializedName("location_code")
  @Expose
  private String locationCode;

  @SerializedName("product_code")
  @Expose
  private String productCode;

  @ForeignKey
      (entity = Product.class,
          parentColumns = "id",
          childColumns = "productId",
          onDelete = CASCADE)
  private Integer productId;

  public Entry(String quantity, String locationCode, String productCode, Integer productId) {
    this.quantity = quantity;
    this.locationCode = locationCode;
    this.productCode = productCode;
    this.productId = productId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  @Override public int compareTo(Entry entry) {
    return this.getProductCode().compareTo(entry.getProductCode());
    //if (this.getProductCode().equals(entry.getProductCode())) {
    //  return 1;
    //} else {
    //  return 0;
    //}
  }
}
