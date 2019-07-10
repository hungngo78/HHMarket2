package com.hhmarket.mobile.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.hhmarket.mobile.model.User;


@Entity(tableName = "user")
public class UserEntity {
    @NonNull
    @PrimaryKey
    public Integer userId;

    public String userName;

    public String password;

    public String email;

    public String firstName;

    public String lastName;

    public String address;

    public String city;

    public String state;

    public String zipCode;

    public UserEntity(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUsername();
        this.password = user.getPassword();
    }

    public UserEntity(int userId, String address, String city, String state, String zipCode) {
        this.userId = userId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
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
    }


}
