package com.Inspira.odo.data.Model;

import java.util.ArrayList;


public class MakOrdersSAccesories {
    private ArrayList<MakOrderAccesories> makOrderAccesoriesArrayList  =new ArrayList<>();
    private  String date;

    public MakOrdersSAccesories(String date,ArrayList<MakOrderAccesories> makOrderAccesoriesArrayList ) {
        this.makOrderAccesoriesArrayList = makOrderAccesoriesArrayList;
        this.date = date;
    }

    public MakOrdersSAccesories() {
    }

    public ArrayList<MakOrderAccesories> getMakOrderAccesoriesArrayList() {
        return makOrderAccesoriesArrayList;
    }

    public void setMakOrderAccesoriesArrayList(ArrayList<MakOrderAccesories> makOrderAccesoriesArrayList) {
        this.makOrderAccesoriesArrayList = makOrderAccesoriesArrayList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
