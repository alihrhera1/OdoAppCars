package com.Inspira.odo.data.Model;

/**
 * Created by shirya on 23/01/18.
 */

public class FavouriteDataAdd {
    private  String post_id ;
    private  String TypeOfRequest;
    private  String car_type;

    public FavouriteDataAdd(String post_id, String typeOfRequest, String car_type) {
        this.post_id = post_id;
        TypeOfRequest = typeOfRequest;
        this.car_type = car_type;
    }

    public FavouriteDataAdd() {
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getTypeOfRequest() {
        return TypeOfRequest;
    }

    public void setTypeOfRequest(String typeOfRequest) {
        TypeOfRequest = typeOfRequest;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }
}
