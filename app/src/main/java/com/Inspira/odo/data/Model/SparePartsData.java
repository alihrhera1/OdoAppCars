package com.Inspira.odo.data.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

//implements Parcelable
public class SparePartsData  implements Parcelable{
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("engineCapacity")
    @Expose
    private String engineCapacity;
    @SerializedName("part")
    @Expose
    private String part;
    @SerializedName("partType")
    @Expose
    private String partType;
    @SerializedName("photosURL")
    @Expose
    private ArrayList<String> photosURL =new ArrayList<>();


    public SparePartsData(String color, String engineCapacity, String part, String partType, ArrayList<String> photosURL) {
        this.color = color;
        this.engineCapacity = engineCapacity;
        this.part = part;
        this.partType = partType;
        this.photosURL = photosURL;
    }

    public SparePartsData() {
    }

    protected SparePartsData(Parcel in) {
        color = in.readString();
        engineCapacity = in.readString();
        part = in.readString();
        partType = in.readString();
        photosURL = in.createStringArrayList();
    }

    public static final Creator<SparePartsData> CREATOR = new Creator<SparePartsData>() {
        @Override
        public SparePartsData createFromParcel(Parcel in) {
            return new SparePartsData(in);
        }

        @Override
        public SparePartsData[] newArray(int size) {
            return new SparePartsData[size];
        }
    };

    public ArrayList<String> getPhotosURL() {
        return photosURL;
    }

    public void setPhotosURL(ArrayList<String> photosURL) {
        this.photosURL = photosURL;
    }



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(color);
        parcel.writeString(engineCapacity);
        parcel.writeString(part);
        parcel.writeString(partType);
        parcel.writeStringList(photosURL);
    }
}