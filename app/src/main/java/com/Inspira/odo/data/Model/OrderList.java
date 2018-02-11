
package com.Inspira.odo.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderList {

    @SerializedName("partType")
    @Expose
    private String partType;
    @SerializedName("part")
    @Expose
    private String part;
    @SerializedName("engineCapacity")
    @Expose
    private String engineCapacity;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("ampere")
    @Expose
    private String ampere;
    @SerializedName("size")
    @Expose
    private String size;

    public OrderList(String partType, String part, String engineCapacity, String color, String ampere, String size) {
        this.partType = partType;
        this.part = part;
        this.engineCapacity = engineCapacity;
        this.color = color;
        this.ampere = ampere;
        this.size = size;
    }

    public OrderList() {
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAmpere() {
        return ampere;
    }

    public void setAmpere(String ampere) {
        this.ampere = ampere;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
