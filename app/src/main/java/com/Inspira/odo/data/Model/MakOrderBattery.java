package com.Inspira.odo.data.Model;

/**
 * Created by shirya on 22/01/18.
 */

public class MakOrderBattery {
    private  String phoneNumber;
    private  String carType;
    private  String carModel;
    private  String carYear;
    private  String date ;
    private  String Favorite ;
    private  String key ;
    private  String amper ;
    private  String Size ;
     private String Run_flot_tyres ;
    private  String poles ;
    private  String Reversed ;
    private  String TyerNumber;
    private  String Type;

    public MakOrderBattery(String phoneNumber, String carType, String carModel, String carYear, String date, String favorite, String key, String amper,
                           String size, String run_flot_tyres, String poles, String reversed, String tyerNumber, String type) {
        this.phoneNumber = phoneNumber;
        this.carType = carType;
        this.carModel = carModel;
        this.carYear = carYear;
        this.date = date;
        Favorite = favorite;
        this.key = key;
        this.amper = amper;
        Size = size;
        Run_flot_tyres = run_flot_tyres;
        this.poles = poles;
        Reversed = reversed;
        TyerNumber = tyerNumber;
        Type = type;
    }

    public MakOrderBattery() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
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
        return Favorite;
    }

    public void setFavorite(String favorite) {
        Favorite = favorite;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAmper() {
        return amper;
    }

    public void setAmper(String amper) {
        this.amper = amper;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getRun_flot_tyres() {
        return Run_flot_tyres;
    }

    public void setRun_flot_tyres(String run_flot_tyres) {
        Run_flot_tyres = run_flot_tyres;
    }

    public String getPoles() {
        return poles;
    }

    public void setPoles(String poles) {
        this.poles = poles;
    }

    public String getReversed() {
        return Reversed;
    }

    public void setReversed(String reversed) {
        Reversed = reversed;
    }

    public String getTyerNumber() {
        return TyerNumber;
    }

    public void setTyerNumber(String tyerNumber) {
        TyerNumber = tyerNumber;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
