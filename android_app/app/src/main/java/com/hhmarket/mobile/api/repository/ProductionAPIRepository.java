package com.hhmarket.mobile.api.repository;

import com.hhmarket.mobile.model.Category;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.ProductDetail;

import java.util.List;

import retrofit2.Callback;

public interface ProductionAPIRepository {
    public void getCategories(Callback<List<Category>> callback);
    public void getProducts(String categoryId, Callback<List<Product>> callback);
    public void getProductDetail(String productId, Callback<List<ProductDetail>> callback);

    public void searchProducts(String criteria, Callback<List<Product>> callback);
}
