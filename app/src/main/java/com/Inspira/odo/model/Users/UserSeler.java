
package com.Inspira.odo.model.Users;

import com.Inspira.odo.data.Model.CompanyOnMap;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class UserSeler {

    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("companyAddress")
    @Expose
    private String companyAddress;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("companyOnMap")
    @Expose
    private CompanyOnMap companyOnMap;
    @SerializedName("companyType")
    @Expose
    private String companyType;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("workingOn")
    @Expose

    Map<String, Map<String,Map<String,String >>  > workingOn = new HashMap<>();

    public UserSeler(String area, String companyAddress, String companyName, CompanyOnMap companyOnMap, String companyType, String email, String fullname, String id, String password, String userType, Boolean verified, Map<String, Map<String, Map<String, String>>> workingOn) {
        this.area = area;
        this.companyAddress = companyAddress;
        this.companyName = companyName;
        this.companyOnMap = companyOnMap;
        this.companyType = companyType;
        this.email = email;
        this.fullname = fullname;
        this.id = id;
        this.password = password;
        this.userType = userType;
        this.verified = verified;
        this.workingOn = workingOn;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Map<String, Map<String, Map<String, String>>> getWorkingOn() {
        return workingOn;
    }

    public void setWorkingOn(Map<String, Map<String, Map<String, String>>> workingOn) {
        this.workingOn = workingOn;
    }
}
