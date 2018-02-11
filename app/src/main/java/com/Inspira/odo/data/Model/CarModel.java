
package com.Inspira.odo.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarModel {

    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("years")
    @Expose
    private List<String> years = null;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
    }

}
