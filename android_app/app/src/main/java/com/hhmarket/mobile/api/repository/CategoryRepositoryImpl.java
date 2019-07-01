package com.hhmarket.mobile.api.repository;

import java.util.List;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.model.Category;

import retrofit2.Call;
import retrofit2.Callback;


public class CategoryRepositoryImpl implements  CategoryRepository {
    private ApiEndpoints apiService;

    public CategoryRepositoryImpl(ApiEndpoints _apiService) {
        this.apiService = _apiService;
    }

    public void getCategories(Callback<List<Category>> callback) {
        Call<List<Category>> call = apiService.getAllCategories();
        call.enqueue(callback);
    }
}
