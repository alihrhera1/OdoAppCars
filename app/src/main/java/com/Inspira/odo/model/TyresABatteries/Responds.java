
package com.Inspira.odo.model.TyresABatteries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responds {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("favourite")
    @Expose
    private Boolean favourite;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("tyresCount")
    @Expose
    private String tyresCount;
    private  String key_Respon ;

    public Responds(String description, String price) {
        this.description = description;
        this.price = price;
    }

    public Responds(String description, String price, String tyresCount) {
        this.description = description;
        this.price = price;
        this.tyresCount = tyresCount;
    }

    public Responds() {
    }

    public String getKey_Respon() {
        return key_Respon;
    }

    public void setKey_Respon(String key_Respon) {
        this.key_Respon = key_Respon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTyresCount() {
        return tyresCount;
    }

    public void setTyresCount(String tyresCount) {
        this.tyresCount = tyresCount;
    }

}
