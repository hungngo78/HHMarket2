package com.hhmarket.mobile.api.repository;

import com.hhmarket.mobile.model.Product;

import java.util.List;

import retrofit2.Callback;

public interface ProductAPIRepository {
    public void getProducts(String categoryId, Callback<List<Product>> callback);
}
