
package com.Inspira.odo.model.spareParts;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import com.Inspira.odo.model.CarDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpareParts   implements Parcelable {

    @SerializedName("buyerPhoneNumber")
    @Expose
    private String buyerPhoneNumber;
    @SerializedName("carDetails")
    @Expose
    private CarDetails carDetails;
    @SerializedName("dateAdded")
    @Expose
    private    long  dateAdded;
    @SerializedName("orderPartType")
    @Expose
    private String orderPartType;
    @SerializedName("partsDescription")
    @Expose
    private ArrayList<PartsDescription> partsDescription = null;
    private  String keyPost ;

    public SpareParts(String buyerPhoneNumber, CarDetails carDetails, Integer dateAdded, String orderPartType, ArrayList<PartsDescription> partsDescription) {
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.carDetails = carDetails;
        this.dateAdded = dateAdded;
        this.orderPartType = orderPartType;
        this.partsDescription = partsDescription;
    }

    public SpareParts() {
    }

    protected SpareParts(Parcel in) {
        buyerPhoneNumber = in.readString();
        orderPartType = in.readString();
        partsDescription = in.createTypedArrayList(PartsDescription.CREATOR);
        keyPost = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(buyerPhoneNumber);
        dest.writeString(orderPartType);
        dest.writeTypedList(partsDescription);
        dest.writeString(keyPost);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SpareParts> CREATOR = new Creator<SpareParts>() {
        @Override
        public SpareParts createFromParcel(Parcel in) {
            return new SpareParts(in);
        }

        @Override
        public SpareParts[] newArray(int size) {
            return new SpareParts[size];
        }
    };

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

    public String getOrderPartType() {
        return orderPartType;
    }

    public void setOrderPartType(String orderPartType) {
        this.orderPartType = orderPartType;
    }

    public ArrayList<PartsDescription> getPartsDescription() {
        return partsDescription;
    }

    public void setPartsDescription(ArrayList<PartsDescription> partsDescription) {
        this.partsDescription = partsDescription;
    }

}
