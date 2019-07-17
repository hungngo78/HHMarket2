package com.hhmarket.mobile.api.repository;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.ProductDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class ProductDetailRepositoryImpl implements ProductDetailAPIRepository {
    private ApiEndpoints apiService;

    public ProductDetailRepositoryImpl(ApiEndpoints _apiService) {
        this.apiService = _apiService;
    }
    @Override
    public void getProductDetail(String productId, Callback<List<ProductDetail>> callback) {
        Call<List<ProductDetail>> call = apiService.getProductDetail(productId);
        call.enqueue(callback);
    }
}
