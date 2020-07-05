package com.veeyaar.myscanner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemModel {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<Value> value = null;
    @SerializedName("@odata.nextLink")
    @Expose
    private String odataNextLink;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }

    public String getOdataNextLink() {
        return odataNextLink;
    }

    public void setOdataNextLink(String odataNextLink) {
        this.odataNextLink = odataNextLink;
    }

    public class Value {

        @SerializedName("ITEMCODE")
        @Expose
        private String iTEMCODE;
        @SerializedName("ITEMNAME")
        @Expose
        private String iTEMNAME;
        @SerializedName("QUANTITY")
        @Expose
        private String qUANTITY;
        @SerializedName("id__")
        @Expose
        private Integer id;
        @SerializedName("PBaseEntry")
        @Expose
        public String pBaseEntry;
        @SerializedName("PBaseNum")
        @Expose
        public String pBaseNum;
        @SerializedName("Color")
        @Expose
        public String color;
        @SerializedName("InvntryUom")
        @Expose
        public String invntryUom;
        @SerializedName("LineId")
        @Expose
        public String lineId;
        @SerializedName("WhsCode")
        @Expose
        public String whsCode;
        @SerializedName("Branch")
        @Expose
        public String branch;
        @SerializedName("CostCenter")
        @Expose
        public String costCenter;
        @SerializedName("InStock")
        @Expose
        public String inStock;
        @SerializedName("VendorCode")
        @Expose
        public String vendorCode;



        public String getITEMCODE() {
            return iTEMCODE;
        }

        public void setITEMCODE(String iTEMCODE) {
            this.iTEMCODE = iTEMCODE;
        }

        public String getITEMNAME() {
            return iTEMNAME;
        }

        public void setITEMNAME(String iTEMNAME) {
            this.iTEMNAME = iTEMNAME;
        }

        public String getQUANTITY() {
            return qUANTITY;
        }

        public void setQUANTITY(String qUANTITY) {
            this.qUANTITY = qUANTITY;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getpBaseEntry() {
            return pBaseEntry;
        }

        public void setpBaseEntry(String pBaseEntry) {
            this.pBaseEntry = pBaseEntry;
        }

        public String getpBaseNum() {
            return pBaseNum;
        }

        public void setpBaseNum(String pBaseNum) {
            this.pBaseNum = pBaseNum;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getInvntryUom() {
            return invntryUom;
        }

        public void setInvntryUom(String invntryUom) {
            this.invntryUom = invntryUom;
        }

        public String getLineId() {
            return lineId;
        }

        public void setLineId(String lineId) {
            this.lineId = lineId;
        }

        public String getWhsCode() {
            return whsCode;
        }

        public void setWhsCode(String whsCode) {
            this.whsCode = whsCode;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getCostCenter() {
            return costCenter;
        }

        public void setCostCenter(String costCenter) {
            this.costCenter = costCenter;
        }

        public String getInStock() {
            return inStock;
        }

        public void setInStock(String inStock) {
            this.inStock = inStock;
        }

        public String getVendorCode() {
            return vendorCode;
        }

        public void setVendorCode(String vendorCode) {
            this.vendorCode = vendorCode;
        }
    }

}

