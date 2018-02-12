package com.Inspira.odo.model;

/**
 * Created by ALi on 10/2/2018.
 */

public class ObjectDrawerItem {

    private int Number;
    private String name;
    private  int photo;

    public ObjectDrawerItem(int number, String name ) {
        this.Number = number;
        this.name = name;
    }
    public ObjectDrawerItem(){}

    public void setPhoto(int photo) {this.photo = photo;}

    public int getPhoto() {
        return photo;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
