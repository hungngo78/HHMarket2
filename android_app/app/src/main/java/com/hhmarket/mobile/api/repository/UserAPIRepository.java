package com.hhmarket.mobile.api.repository;

import com.hhmarket.mobile.model.User;


import retrofit2.Callback;

public interface UserAPIRepository {

    public void getUserInfo(String userName, String password, Callback<User> callback);


}
