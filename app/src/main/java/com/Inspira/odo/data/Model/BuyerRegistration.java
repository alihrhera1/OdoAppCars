package com.Inspira.odo.data.Model;



public class BuyerRegistration {
       String phoneNumber;
       String fullName;
       String email;
       String password;
       String userType;

    public BuyerRegistration(String phoneNumber, String fullName, String email,
                             String password, String userType) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.userType = userType;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
