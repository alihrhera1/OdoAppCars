package com.Inspira.odo.data.Model;

import java.util.ArrayList;

/**
 * Created by shirya on 19/01/18.
 */

public class MakOrderSparePart {
    private String phoneNumber;
    private String carType;
    private String carModel;
    private String carYear;
    private ArrayList<SparePartsData> orderList =new ArrayList<>();
    private  ArrayList<String> orderImages = new ArrayList<>();
    private  String date ;
    String Favorite ;
    private  String key ;

    public MakOrderSparePart(String phoneNumber, String carType, String carModel, String carYear,  ArrayList<SparePartsData> orderList,
                    ArrayList<String> orderImages , String date , String Favorite) {
        this.phoneNumber = phoneNumber;
        this.carType = carType;
        this.carModel = carModel;
        this.carYear = carYear;
        this.orderList = orderList;
        this.orderImages = orderImages;
        this.date=date;
        this.Favorite=Favorite;
    }

    public MakOrderSparePart() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String isFavorite() {
        return Favorite;
    }

    public void setFavorite(String favorite) {
        Favorite = favorite;
    }

    public String getDate() {
        return date;
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

    public  ArrayList<SparePartsData>  getOrderList() {
        return orderList;
    }

    public void setOrderList(  ArrayList<SparePartsData>  orderList) {
        this.orderList = orderList;
    }

    public  ArrayList<String>  getOrderImages() {
        return orderImages;
    }

    public void setOrderImages( ArrayList<String> orderImages) {
        this.orderImages = orderImages;
    }

}
