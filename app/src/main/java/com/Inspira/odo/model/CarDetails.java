
package com.Inspira.odo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarDetails   implements Parcelable {

    @SerializedName("carModel")
    @Expose
    private String carModel;
    @SerializedName("carType")
    @Expose
    private String carType;
    @SerializedName("carYear")
    @Expose
    private String carYear;
    @SerializedName("engineCapacity")
    @Expose
    private String engineCapacity;
    @SerializedName("partColor")
    @Expose
    private String partColor;

    public CarDetails(String carModel, String carType, String carYear, String engineCapacity, String partColor) {
        this.carModel = carModel;
        this.carType = carType;
        this.carYear = carYear;
        this.engineCapacity = engineCapacity;
        this.partColor = partColor;
    }

    public CarDetails() {
    }

    protected CarDetails(Parcel in) {
        carModel = in.readString();
        carType = in.readString();
        carYear = in.readString();
        engineCapacity = in.readString();
        partColor = in.readString();
    }

    public static final Creator<CarDetails> CREATOR = new Creator<CarDetails>() {
        @Override
        public CarDetails createFromParcel(Parcel in) {
            return new CarDetails(in);
        }

        @Override
        public CarDetails[] newArray(int size) {
            return new CarDetails[size];
        }
    };

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

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getPartColor() {
        return partColor;
    }

    public void setPartColor(String partColor) {
        this.partColor = partColor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(carModel);
        parcel.writeString(carType);
        parcel.writeString(carYear);
        parcel.writeString(engineCapacity);
        parcel.writeString(partColor);
    }
}
