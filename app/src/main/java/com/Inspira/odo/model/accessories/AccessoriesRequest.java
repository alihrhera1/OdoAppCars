
package com.Inspira.odo.model.accessories;

import android.os.Parcel;
import android.os.Parcelable;

import com.Inspira.odo.model.CarDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AccessoriesRequest  implements Parcelable {

    @SerializedName("buyerPhoneNumber")
    @Expose
    private String buyerPhoneNumber;
    @SerializedName("dateAdded")
    @Expose
    private long dateAdded;
    @SerializedName("orderPartType")
    @Expose
    private String orderPartType;
    @SerializedName("carDetails")
    @Expose
    private CarDetails carDetails;
    @SerializedName("accessoriesDescription")
    @Expose
    private ArrayList<accessoriesDescription> accessoriesDescription = null;

    private  String keyPost ;

    public AccessoriesRequest(String buyerPhoneNumber, Integer dateAdded, String orderPartType, CarDetails carDetails, ArrayList<accessoriesDescription> accessoriesDescription) {
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.dateAdded = dateAdded;
        this.orderPartType = orderPartType;
        this.carDetails = carDetails;
        this.accessoriesDescription = accessoriesDescription;
    }

    public AccessoriesRequest() {
    }

    protected AccessoriesRequest(Parcel in) {
        buyerPhoneNumber = in.readString();
        orderPartType = in.readString();
        accessoriesDescription = in.createTypedArrayList(com.Inspira.odo.model.accessories.accessoriesDescription.CREATOR);
        keyPost = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(buyerPhoneNumber);
        dest.writeString(orderPartType);
        dest.writeTypedList(accessoriesDescription);
        dest.writeString(keyPost);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AccessoriesRequest> CREATOR = new Creator<AccessoriesRequest>() {
        @Override
        public AccessoriesRequest createFromParcel(Parcel in) {
            return new AccessoriesRequest(in);
        }

        @Override
        public AccessoriesRequest[] newArray(int size) {
            return new AccessoriesRequest[size];
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

    public CarDetails getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetails carDetails) {
        this.carDetails = carDetails;
    }

    public ArrayList<accessoriesDescription> getAccessoriesDescription() {
        return accessoriesDescription;
    }

    public void setAccessoriesDescription(ArrayList<accessoriesDescription> accessoriesDescription) {
        this.accessoriesDescription = accessoriesDescription;
    }
}
