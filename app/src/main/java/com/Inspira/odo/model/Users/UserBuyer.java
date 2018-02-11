
package com.Inspira.odo.model.Users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserBuyer {

    @SerializedName("carModel")
    @Expose
    private String carModel;
    @SerializedName("carType")
    @Expose
    private String carType;
    @SerializedName("carYear")
    @Expose
    private String carYear;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("engineCapacity")
    @Expose
    private String engineCapacity;
    @SerializedName("favourites")
    @Expose
    private String favourites;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("partColor")
    @Expose
    private String partColor;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("userType")
    @Expose
    private String userType;


    public UserBuyer(String carModel, String carType, String carYear, String email, String engineCapacity, String favourites, String fullName,
                     String id, String partColor, String password, String userType) {
        this.carModel = carModel;
        this.carType = carType;
        this.carYear = carYear;
        this.email = email;
        this.engineCapacity = engineCapacity;
        this.favourites = favourites;
        this.fullName = fullName;
        this.id = id;
        this.partColor = partColor;
        this.password = password;
        this.userType = userType;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getFavourites() {
        return favourites;
    }

    public void setFavourites(String favourites) {
        this.favourites = favourites;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartColor() {
        return partColor;
    }

    public void setPartColor(String partColor) {
        this.partColor = partColor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }



}
