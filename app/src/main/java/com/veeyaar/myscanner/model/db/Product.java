package com.veeyaar.myscanner.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

@Entity(tableName = "products")
public class Product {

  @PrimaryKey(autoGenerate = false)
  @ColumnInfo(name = "id")
  private int id;
  @SerializedName("item_code")
  @Expose
  private String itemCode;
  @SerializedName("item_name")
  @Expose
  private String itemName;
  @SerializedName("item_quant")
  @Expose
  private String itemQuant;
  @Ignore
  private List<Entry> entryList = null;

  @Ignore
  public Product(int id, String itemCode, String itemName, String itemQuant,List<Entry> entryList) {
    this.id = id;
    this.itemCode = itemCode;
    this.itemName = itemName;
    this.entryList = entryList;
    this.itemQuant = itemQuant;
  }

  public Product() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public String getItemQuant() {
    return itemQuant;
  }

  public void setItemQuant(String itemQuant) {
    this.itemQuant = itemQuant;
  }

  public List<Entry> getEntryList() {
    return entryList;
  }

  public void setEntryList(List<Entry> entryList) {
    this.entryList = entryList;
  }
}
