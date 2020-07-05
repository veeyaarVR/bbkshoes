package com.veeyaar.myscanner.model.db;

import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;

public class ProductWithEntries {
  @Embedded
  public Product product;
  @Relation(
      parentColumn = "id",
      entityColumn = "productId",
      entity = Entry.class
  )
  private List<Entry> entryList;

  public List<Entry> getEntryList() {
    return entryList;
  }

  public Product getProduct() {
    return product;
  }

  public void setEntryList(List<Entry> entryList) {
    this.entryList = entryList;
  }
}
