package com.hhmarket.mobile.api.repository;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.model.Category;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.ProductDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ProductionAPIRepositoryImpl implements ProductionAPIRepository {
    private ApiEndpoints apiService;

    public ProductionAPIRepositoryImpl(ApiEndpoints _apiService) {
        this.apiService = _apiService;
    }

    @Override
    public void getCategories(Callback<List<Category>> callback) {
        Call<List<Category>> call = apiService.getAllCategories();
        call.enqueue(callback);
    }

    @Override
    public void getProducts(String categoryId, Callback<List<Product>> callback) {
        Call<List<Product>> call = apiService.getAllProducts(categoryId);
        call.enqueue(callback);
    }

    @Override
    public void getProductDetail(String productId, Callback<List<ProductDetail>> callback) {
        Call<List<ProductDetail>> call = apiService.getProductDetail(productId);
        call.enqueue(callback);
    }
}
