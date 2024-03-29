package com.hhmarket.mobile.api.repository;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.model.User;

import retrofit2.Call;
import retrofit2.Callback;


public class UserAPIRepositoryImpl implements UserAPIRepository {
    private ApiEndpoints apiService;

    public UserAPIRepositoryImpl(ApiEndpoints _apiService) {
        this.apiService = _apiService;
    }


    @Override
    public void getUserInfo(String userName, String password, Callback<User> callback) {
        Call<User> call = apiService.login(userName, password);
        call.enqueue(callback);
    }
}
