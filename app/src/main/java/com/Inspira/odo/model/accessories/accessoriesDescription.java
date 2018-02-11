package com.Inspira.odo.model.accessories;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shirya on 05/02/18.
 */

public class accessoriesDescription  implements Parcelable {

    @SerializedName("part")
    @Expose
    private String part;
    @SerializedName("photosURL")
    @Expose
    private ArrayList<String> photosURL = new ArrayList<>();

    public accessoriesDescription(String part, ArrayList<String> photosURL) {
        this.part = part;
        this.photosURL = photosURL;
    }

    public accessoriesDescription() {
    }

    protected accessoriesDescription(Parcel in) {
        part = in.readString();
        photosURL = in.createStringArrayList();
    }

    public static final Creator<accessoriesDescription> CREATOR = new Creator<accessoriesDescription>() {
        @Override
        public accessoriesDescription createFromParcel(Parcel in) {
            return new accessoriesDescription(in);
        }

        @Override
        public accessoriesDescription[] newArray(int size) {
            return new accessoriesDescription[size];
        }
    };

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
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
        parcel.writeString(part);
        parcel.writeStringList(photosURL);
    }
}
