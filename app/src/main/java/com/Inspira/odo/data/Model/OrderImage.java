
package com.Inspira.odo.data.Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class OrderImage {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("originalName")
    @Expose
    private String originalName;
    @SerializedName("imagesUrl")
    @Expose
    Map<String,Bitmap > imagesUrl= new HashMap<>();
//    public OrderImage(String originalName) {
//        this.originalName = originalName;
//    }

    public OrderImage() {
    }

    public OrderImage(Map<String, Bitmap> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public Map<String, Bitmap> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(Map<String, Bitmap> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

}
