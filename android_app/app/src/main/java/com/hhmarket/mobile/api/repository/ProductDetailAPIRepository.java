package com.hhmarket.mobile.api.repository;

import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.ProductDetail;

import java.util.List;

import retrofit2.Callback;

public interface ProductDetailAPIRepository {
    public void getProductDetail(String productId, Callback<List<ProductDetail>> callback);
}
