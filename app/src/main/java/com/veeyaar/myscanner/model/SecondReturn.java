package com.veeyaar.myscanner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SecondReturn {
  @SerializedName("U_DocDate")
  @Expose
  public String uDocDate;
  @SerializedName("U_BaseNum")
  @Expose
  public String uBaseNum;
  @SerializedName("U_BaseEntry")
  @Expose
  public String uBaseEntry;
  @SerializedName("U_BaseSeries")
  @Expose
  public String uBaseSeries;
  @SerializedName("U_Process")
  @Expose
  public String uProcess;
  @SerializedName("U_CardCode")
  @Expose
  public String uCardCode;
  @SerializedName("U_TarNum")
  @Expose
  public String uTarNum;
  @SerializedName("U_TarEntry")
  @Expose
  public String uTarEntry;
  @SerializedName("AIS_GDI1Collection")
  @Expose
  public List<AISGDI1Collection> aISGDI1Collection = null;

  public void setuDocDate(String uDocDate) {
    this.uDocDate = uDocDate;
  }

  public void setuBaseNum(String uBaseNum) {
    this.uBaseNum = uBaseNum;
  }

  public void setuBaseEntry(String uBaseEntry) {
    this.uBaseEntry = uBaseEntry;
  }

  public void setuBaseSeries(String uBaseSeries) {
    this.uBaseSeries = uBaseSeries;
  }

  public void setuProcess(String uProcess) {
    this.uProcess = uProcess;
  }

  public void setuCardCode(String uCardCode) {
    this.uCardCode = uCardCode;
  }

  public void setuTarNum(String uTarNum) {
    this.uTarNum = uTarNum;
  }

  public void setuTarEntry(String uTarEntry) {
    this.uTarEntry = uTarEntry;
  }

  public void setaISGDI1Collection(List<AISGDI1Collection> aISGDI1Collection) {
    this.aISGDI1Collection = aISGDI1Collection;
  }

  public static class AISGDI1Collection {

    @SerializedName("U_ItemCode")
    @Expose
    public String uItemCode;
    @SerializedName("U_ItemName")
    @Expose
    public String uItemName;
    @SerializedName("U_Colour")
    @Expose
    public String uColour;
    @SerializedName("U_ActualQty")
    @Expose
    public String uActualQty;
    @SerializedName("U_Quantity")
    @Expose
    public String uQuantity;
    @SerializedName("U_UOM")
    @Expose
    public String uUOM;
    @SerializedName("U_RMLineID")
    @Expose
    public String uRMLineID;
    @SerializedName("U_WhsCode")
    @Expose
    public String uWhsCode;
    @SerializedName("U_Branch")
    @Expose
    public String uBranch;
    @SerializedName("U_CostCenter")
    @Expose
    public String uCostCenter;
    @SerializedName("U_PBaseEntry")
    @Expose
    public String uPBaseEntry;
    @SerializedName("U_PBaseNum")
    @Expose
    public String uPBaseNum;
    @SerializedName("U_Instock")
    @Expose
    public String uInstock;
    @SerializedName("U_VendorCode")
    @Expose
    public String uVendorCode;
    @SerializedName("U_Remarks")
    @Expose
    public String uRemarks;
    @SerializedName("U_HideQty")
    @Expose
    public String uHideQty;
    @SerializedName("U_SideQty")
    @Expose
    public String uSideQty;

    public void setuItemCode(String uItemCode) {
      this.uItemCode = uItemCode;
    }

    public void setuItemName(String uItemName) {
      this.uItemName = uItemName;
    }

    public void setuColour(String uColour) {
      this.uColour = uColour;
    }

    public void setuActualQty(String uActualQty) {
      this.uActualQty = uActualQty;
    }

    public void setuQuantity(String uQuantity) {
      this.uQuantity = uQuantity;
    }

    public void setuUOM(String uUOM) {
      this.uUOM = uUOM;
    }

    public void setuRMLineID(String uRMLineID) {
      this.uRMLineID = uRMLineID;
    }

    public void setuWhsCode(String uWhsCode) {
      this.uWhsCode = uWhsCode;
    }

    public void setuBranch(String uBranch) {
      this.uBranch = uBranch;
    }

    public void setuCostCenter(String uCostCenter) {
      this.uCostCenter = uCostCenter;
    }

    public void setuPBaseEntry(String uPBaseEntry) {
      this.uPBaseEntry = uPBaseEntry;
    }

    public void setuPBaseNum(String uPBaseNum) {
      this.uPBaseNum = uPBaseNum;
    }

    public void setuInstock(String uInstock) {
      this.uInstock = uInstock;
    }

    public void setuVendorCode(String uVendorCode) {
      this.uVendorCode = uVendorCode;
    }

    public void setuRemarks(String uRemarks) {
      this.uRemarks = uRemarks;
    }

    public void setuHideQty(String uHideQty) {
      this.uHideQty = uHideQty;
    }

    public void setuSideQty(String uSideQty) {
      this.uSideQty = uSideQty;
    }
  }

}
