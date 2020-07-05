package com.veeyaar.myscanner.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FirstReturnResponse {

  @SerializedName("DocType")
  @Expose
  public String docType;
  @SerializedName("BPL_IDAssignedToInvoice")
  @Expose
  public String bPLIDAssignedToInvoice;
  @SerializedName("U_BaseObject")
  @Expose
  public String uBaseObject;
  @SerializedName("U_BaseNum")
  @Expose
  public String uBaseNum;
  @SerializedName("U_BaseSeries")
  @Expose
  public String uBaseSeries;
  @SerializedName("U_CardCode")
  @Expose
  public String uCardCode;
  @SerializedName("Comments")
  @Expose
  public String comments;
  @SerializedName("DocumentLines")
  @Expose
  public List<DocumentLine> documentLines = null;

  @NonNull @Override public String toString() {
    return super.toString();
  }

  public void setDocType(String docType) {
    this.docType = docType;
  }

  public void setbPLIDAssignedToInvoice(String bPLIDAssignedToInvoice) {
    this.bPLIDAssignedToInvoice = bPLIDAssignedToInvoice;
  }

  public void setuBaseObject(String uBaseObject) {
    this.uBaseObject = uBaseObject;
  }

  public void setuBaseNum(String uBaseNum) {
    this.uBaseNum = uBaseNum;
  }

  public void setuBaseSeries(String uBaseSeries) {
    this.uBaseSeries = uBaseSeries;
  }

  public void setuCardCode(String uCardCode) {
    this.uCardCode = uCardCode;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public void setDocumentLines(
      List<DocumentLine> documentLines) {
    this.documentLines = documentLines;
  }

  public static class DocumentLine {

    @SerializedName("ItemCode")
    @Expose
    public String itemCode;
    @SerializedName("BaseLine")
    @Expose
    public Integer baseLine;
    @SerializedName("Quantity")
    @Expose
    public Double quantity;
    @SerializedName("WarehouseCode")
    @Expose
    public String warehouseCode;
    @SerializedName("U_Colour")
    @Expose
    public String uColour;
    @SerializedName("U_VendorCode")
    @Expose
    public String uVendorCode;
    @SerializedName("U_Branch")
    @Expose
    public String uBranch;
    @SerializedName("U_PBaseNum")
    @Expose
    public String uPBaseNum;
    @SerializedName("U_PBaseEntry")
    @Expose
    public String uPBaseEntry;
    @SerializedName("U_RMLineID")
    @Expose
    public String uRMLineID;
    @SerializedName("U_ActualQty")
    @Expose
    public String uActualQty;
    @SerializedName("BatchNumbers")
    @Expose
    public List<BatchNumber> batchNumbers = null;
    @SerializedName("DocumentLinesBinAllocations")
    @Expose
    public List<DocumentLinesBinAllocation> documentLinesBinAllocations = null;

    public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
    }

    public void setBaseLine(Integer baseLine) {
      this.baseLine = baseLine;
    }

    public void setQuantity(Double quantity) {
      this.quantity = quantity;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }

    public void setuColour(String uColour) {
      this.uColour = uColour;
    }

    public void setuVendorCode(String uVendorCode) {
      this.uVendorCode = uVendorCode;
    }

    public void setuBranch(String uBranch) {
      this.uBranch = uBranch;
    }

    public void setuPBaseNum(String uPBaseNum) {
      this.uPBaseNum = uPBaseNum;
    }

    public void setuPBaseEntry(String uPBaseEntry) {
      this.uPBaseEntry = uPBaseEntry;
    }

    public void setuRMLineID(String uRMLineID) {
      this.uRMLineID = uRMLineID;
    }

    public void setuActualQty(String uActualQty) {
      this.uActualQty = uActualQty;
    }

    public void setBatchNumbers(
        List<BatchNumber> batchNumbers) {
      this.batchNumbers = batchNumbers;
    }

    public void setDocumentLinesBinAllocations(
        List<DocumentLinesBinAllocation> documentLinesBinAllocations) {
      this.documentLinesBinAllocations = documentLinesBinAllocations;
    }
  }

  public static class BatchNumber {

    @SerializedName("BatchNumber")
    @Expose
    public String batchNumber;
    @SerializedName("Quantity")
    @Expose
    public String quantity;

    public void setBatchNumber(String batchNumber) {
      this.batchNumber = batchNumber;
    }

    public void setQuantity(String quantity) {
      this.quantity = quantity;
    }
  }

  public static class DocumentLinesBinAllocation {

    @SerializedName("BinAbsEntry")
    @Expose
    public String binAbsEntry;
    @SerializedName("Quantity")
    @Expose
    public String quantity;
    @SerializedName("SerialAndBatchNumbersBaseLine")
    @Expose
    public String serialAndBatchNumbersBaseLine;

    public void setBinAbsEntry(String binAbsEntry) {
      this.binAbsEntry = binAbsEntry;
    }

    public void setQuantity(String quantity) {
      this.quantity = quantity;
    }

    public void setSerialAndBatchNumbersBaseLine(String serialAndBatchNumbersBaseLine) {
      this.serialAndBatchNumbersBaseLine = serialAndBatchNumbersBaseLine;
    }
  }
}