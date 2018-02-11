package com.Inspira.odo.data.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class MakOrderAccesories implements Parcelable {
    private  String phoneNumber;
    private  String carType;
    private  String carModel;
    private  String carYear;
    private  String date ;
    private  String Favorite ;
    private  String key ;
    private  String Part ;
    private ArrayList<String> photosURL =new ArrayList<>();

    public MakOrderAccesories(String phoneNumber, String carType, String carModel, String carYear, String date,
                              String favorite, String key, String part, ArrayList<String> photosURL) {
        this.phoneNumber = phoneNumber;
        this.carType = carType;
        this.carModel = carModel;
        this.carYear = carYear;
        this.date = date;
        Favorite = favorite;
        this.key = key;
        Part = part;
        this.photosURL = photosURL;
    }

    public MakOrderAccesories() {
    }

    protected MakOrderAccesories(Parcel in) {
        phoneNumber = in.readString();
        carType = in.readString();
        carModel = in.readString();
        carYear = in.readString();
        date = in.readString();
        Favorite = in.readString();
        key = in.readString();
        Part = in.readString();
        photosURL = in.createStringArrayList();
    }

    public static final Creator<MakOrderAccesories> CREATOR = new Creator<MakOrderAccesories>() {
        @Override
        public MakOrderAccesories createFromParcel(Parcel in) {
            return new MakOrderAccesories(in);
        }

        @Override
        public MakOrderAccesories[] newArray(int size) {
            return new MakOrderAccesories[size];
        }
    };

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

    public String getPart() {
        return Part;
    }

    public void setPart(String part) {
        Part = part;
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
        parcel.writeString(phoneNumber);
        parcel.writeString(carType);
        parcel.writeString(carModel);
        parcel.writeString(carYear);
        parcel.writeString(date);
        parcel.writeString(Favorite);
        parcel.writeString(key);
        parcel.writeString(Part);
        parcel.writeStringList(photosURL);
    }
}
