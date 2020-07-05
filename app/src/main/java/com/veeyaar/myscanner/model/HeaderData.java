package com.veeyaar.myscanner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HeaderData {

  @SerializedName("@odata.context")
  @Expose
  public String odataContext;
  @SerializedName("value")
  @Expose
  public List<Value> value = null;

  public String getOdataContext() {
    return odataContext;
  }

  public List<Value> getValue() {
    return value;
  }

  public class Value {

    @SerializedName("CardCode")
    @Expose
    public String cardCode;
    @SerializedName("CardName")
    @Expose
    public String cardName;
    @SerializedName("FromWhsCode")
    @Expose
    public String fromWhsCode;
    @SerializedName("ToWhsCode")
    @Expose
    public String toWhsCode;
    @SerializedName("FrmBranch")
    @Expose
    public String frmBranch;
    @SerializedName("ToBranch")
    @Expose
    public String toBranch;
    @SerializedName("DocEntry")
    @Expose
    public String docEntry;
    @SerializedName("DocNum")
    @Expose
    public String docNum;
    @SerializedName("Object")
    @Expose
    public String object;
    @SerializedName("Series")
    @Expose
    public String series;
    @SerializedName("Process")
    @Expose
    public String process;
    @SerializedName("id__")
    @Expose
    public Integer id;

    public String getCardCode() {
      return cardCode;
    }

    public String getCardName() {
      return cardName;
    }

    public String getFromWhsCode() {
      return fromWhsCode;
    }

    public String getToWhsCode() {
      return toWhsCode;
    }

    public String getFrmBranch() {
      return frmBranch;
    }

    public String getToBranch() {
      return toBranch;
    }

    public String getDocEntry() {
      return docEntry;
    }

    public String getDocNum() {
      return docNum;
    }

    public String getObject() {
      return object;
    }

    public String getSeries() {
      return series;
    }

    public String getProcess() {
      return process;
    }

    public Integer getId() {
      return id;
    }
  }
}