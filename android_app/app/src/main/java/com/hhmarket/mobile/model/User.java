package com.hhmarket.mobile.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class User  implements UserInterface, Serializable {

    @SerializedName("userId")
    public Integer userId;

    @SerializedName("userName")
    public String userName;

    @SerializedName("password")
    public String password;

    @SerializedName("email")
    public String email;

    @SerializedName("firstName")
    public String firstName;

    @SerializedName("lastName")
    public String lastName;

    @SerializedName("address")
    public String address;

    @SerializedName("city")
    public String city;

    @SerializedName("state")
    public String state;

    @SerializedName("zipCode")
    public String zipCode;

    @Override
    public String getAddress() {
        return address + "," + city + "," + state + "," + zipCode;
    }

    @Override
    public String getFullname() {
        return firstName + " " + lastName;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /*
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    } */
}
