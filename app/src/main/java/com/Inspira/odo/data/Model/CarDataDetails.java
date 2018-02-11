
package com.Inspira.odo.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarDataDetails {

    @SerializedName("carModel")
    @Expose
    private String carModel;
    @SerializedName("carType")
    @Expose
    private String carType;
    @SerializedName("carYear")
    @Expose
    private String carYear;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("favorite")
    @Expose
    private String favorite;

    public CarDataDetails(String carModel, String carType, String carYear, String date, String favorite) {
        this.carModel = carModel;
        this.carType = carType;
        this.carYear = carYear;
        this.date = date;
        this.favorite = favorite;
    }

    public CarDataDetails() {

    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

}
