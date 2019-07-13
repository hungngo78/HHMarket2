package com.hhmarket.mobile.api;

import java.util.List;

import com.hhmarket.mobile.model.Category;
import com.hhmarket.mobile.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiEndpoints {
    @GET("hello")
    Call<String> hello();

    @GET("category/get_all_categories")
    Call<List<Category>> getAllCategories();

    @GET("account/login")
    Call<User> login(@Query("username") String username, @Query("password") String password);


}
