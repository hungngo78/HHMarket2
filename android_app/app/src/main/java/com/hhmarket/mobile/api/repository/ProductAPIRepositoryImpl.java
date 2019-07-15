package com.hhmarket.mobile.api.repository;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class ProductAPIRepositoryImpl implements ProductAPIRepository {
    private ApiEndpoints apiService;

    public ProductAPIRepositoryImpl(ApiEndpoints _apiService) {
        this.apiService = _apiService;
    }

    public void getProducts(String categoryId, Callback<List<Product>> callback) {
        Call<List<Product>> call = apiService.getAllProducts(categoryId);
        call.enqueue(callback);
    }
}
