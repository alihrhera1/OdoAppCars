
package com.Inspira.odo.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkingOn {

    @SerializedName("carType")
    @Expose
    private String carType;
    @SerializedName("carModels")
    @Expose
    private List<CarModel> carModels = null;
    Map<String, Map<String,Map<String,String > >  > CarsWorking = new HashMap<>();

    public WorkingOn(   Map<String, Map<String,Map<String,String >>  > carsWorking) {
        CarsWorking = carsWorking;
    }

    public   Map<String, Map<String,Map<String,String > >  > getCarsWorking() {
        return CarsWorking;
    }

    public void setCarsWorking(   Map<String, Map<String,Map<String,String > >  > carsWorking) {
        CarsWorking = carsWorking;
    }

    public String getCarType() {
        return carType;
    }

    public WorkingOn(String carType, List<CarModel> carModels) {
        this.carType = carType;
        this.carModels = carModels;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }

}
