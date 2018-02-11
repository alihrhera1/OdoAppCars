
package com.Inspira.odo.model.TyresABatteries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderList {

    @SerializedName("runFlatTyres")
    @Expose
    private Boolean runFlatTyres;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("tyresNumber")
    @Expose
    private String tyresNumber;

    @SerializedName("isReversed")
    @Expose
    private Boolean isReversed;
    @SerializedName("poleSize")
    @Expose
    private String poleSize;

    public OrderList(Boolean runFlatTyres, String size, String tyresNumber) {
        this.runFlatTyres = runFlatTyres;
        this.size = size;
        this.tyresNumber = tyresNumber;
    }

    public OrderList(String size, Boolean isReversed, String poleSize) {
        this.size = size;
        this.isReversed = isReversed;
        this.poleSize = poleSize;
    }

    public OrderList() {
    }

    public Boolean getRunFlatTyres() {
        return runFlatTyres;
    }

    public void setRunFlatTyres(Boolean runFlatTyres) {
        this.runFlatTyres = runFlatTyres;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTyresNumber() {
        return tyresNumber;
    }

    public void setTyresNumber(String tyresNumber) {
        this.tyresNumber = tyresNumber;
    }

    public Boolean getReversed() {
        return isReversed;
    }

    public void setReversed(Boolean reversed) {
        isReversed = reversed;
    }

    public String getPoleSize() {
        return poleSize;
    }

    public void setPoleSize(String poleSize) {
        this.poleSize = poleSize;
    }
}
