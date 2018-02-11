package com.Inspira.odo.model.accessories;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shirya on 09/02/18.
 */

public class RespondsAC implements Parcelable {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("photosURL")
    @Expose
    private ArrayList<String> photosURL = null;
    private  String key_Respon ;

    public RespondsAC(String description, String price, ArrayList<String> photosURL) {
        this.description = description;
        this.price = price;
        this.photosURL = photosURL;
    }

    public RespondsAC() {
    }

    protected RespondsAC(Parcel in) {
        description = in.readString();
        price = in.readString();
        photosURL = in.createStringArrayList();
    }

    public static final Creator<RespondsAC> CREATOR = new Creator<RespondsAC>() {
        @Override
        public RespondsAC createFromParcel(Parcel in) {
            return new RespondsAC(in);
        }

        @Override
        public RespondsAC[] newArray(int size) {
            return new RespondsAC[size];
        }
    };

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<String> getPhotosURL() {
        return photosURL;
    }

    public void setPhotosURL(ArrayList<String> photosURL) {
        this.photosURL = photosURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeStringList(photosURL);
    }
}


