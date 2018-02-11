package com.Inspira.odo.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class SellerRegistration {

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("hashVal")
    @Expose
    private String hashVal;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("companyAddress")
    @Expose
    private String companyAddress;
    @SerializedName("companyArea")
    @Expose
    private String companyArea;
    @SerializedName("companyOnMap")
    @Expose
    private CompanyOnMap companyOnMap;
    @SerializedName("companyType")
    @Expose
    private String companyType;
    @SerializedName("workingOn")
    @Expose
    Map<String, Map<String,Map<String,String > >  > workingOn = null;

    public SellerRegistration(String phoneNumber, String fullName, String email, String password,
                              String companyName, String companyAddress,
                              String companyArea, CompanyOnMap companyOnMap, String companyType,
                              Map<String, Map<String,Map<String,String > >  > workingOn) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.userType = "seller";
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyOnMap = companyOnMap;
        this.companyType = companyType;
        this.workingOn = workingOn;
        this.companyArea =companyArea ;
    }

    public String getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashVal() {
        return hashVal;
    }

    public void setHashVal(String hashVal) {
        this.hashVal = hashVal;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public CompanyOnMap getCompanyOnMap() {
        return companyOnMap;
    }

    public void setCompanyOnMap(CompanyOnMap companyOnMap) {
        this.companyOnMap = companyOnMap;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public
    Map<String, Map<String,Map<String,String > >  >getWorkingOn() {
        return workingOn;
    }

    public void setWorkingOn(
            Map<String, Map<String,Map<String,String > >  >workingOn) {
        this.workingOn = workingOn;
    }

}
