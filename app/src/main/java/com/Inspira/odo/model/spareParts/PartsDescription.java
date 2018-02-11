
package com.Inspira.odo.model.spareParts;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PartsDescription implements Parcelable {
    @SerializedName("part")
    @Expose
    private String part;
    @SerializedName("photosURL")
    @Expose
    private ArrayList<String> photosURL = new ArrayList<>();


    public PartsDescription(String part, ArrayList<String> photosURL) {
        this.part = part;
        this.photosURL = photosURL;
    }

    protected PartsDescription(Parcel in) {
        part = in.readString();
        photosURL = in.createStringArrayList();
    }

    public PartsDescription() {
    }

    public static final Creator<PartsDescription> CREATOR = new Creator<PartsDescription>() {
        @Override
        public PartsDescription createFromParcel(Parcel in) {
            return new PartsDescription(in);
        }

        @Override
        public PartsDescription[] newArray(int size) {
            return new PartsDescription[size];
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
