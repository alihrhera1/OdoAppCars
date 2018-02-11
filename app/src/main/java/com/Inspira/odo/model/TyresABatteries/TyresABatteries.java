
package com.Inspira.odo.model.TyresABatteries;

import com.Inspira.odo.model.CarDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TyresABatteries {

    @SerializedName("buyerPhoneNumber")
    @Expose
    private String buyerPhoneNumber;
    @SerializedName("carDetails")
    @Expose
    private CarDetails carDetails;
    @SerializedName("dateAdded")
    @Expose
    private long dateAdded;
    @SerializedName("orderList")
    @Expose
    private  OrderList orderList;
    @SerializedName("orderPartType")
    @Expose
    private String orderPartType;
    private  String keyPost ;


    public TyresABatteries(String buyerPhoneNumber, CarDetails carDetails, Integer dateAdded,  OrderList orderList, String orderPartType) {
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.carDetails = carDetails;
        this.dateAdded = dateAdded;
        this.orderList = orderList;
        this.orderPartType = orderPartType;
    }

    public TyresABatteries() {
    }

    public String getKeyPost() {
        return keyPost;
    }

    public void setKeyPost(String keyPost) {
        this.keyPost = keyPost;
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumber = buyerPhoneNumber;
    }

    public CarDetails getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetails carDetails) {
        this.carDetails = carDetails;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public  OrderList getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
    }

    public String getOrderPartType() {
        return orderPartType;
    }

    public void setOrderPartType(String orderPartType) {
        this.orderPartType = orderPartType;
    }

}
