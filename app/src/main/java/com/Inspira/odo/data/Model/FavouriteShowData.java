package com.Inspira.odo.data.Model;


public class FavouriteShowData {
    private  String key_post ;
    private  String carType;
    private  String carModel;
    private  String ImageUrl ;
    private  String part_ofCar ;



    public FavouriteShowData(String key_post, String carType, String carModel, String imageUrl, String part_ofCar) {
        this.key_post = key_post;
        this.carType = carType;
        this.carModel = carModel;
        ImageUrl = imageUrl;
        this.part_ofCar = part_ofCar;

    }
    public String getKey_post() {
        return key_post;
    }

    public void setKey_post(String key_post) {
        this.key_post = key_post;
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

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getPart_ofCar() {
        return part_ofCar;
    }

    public void setPart_ofCar(String part_ofCar) {
        this.part_ofCar = part_ofCar;
    }
}
