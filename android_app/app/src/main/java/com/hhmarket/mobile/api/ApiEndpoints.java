package com.hhmarket.mobile.api;

import java.util.List;

import com.hhmarket.mobile.model.Category;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.ProductDetail;
import com.hhmarket.mobile.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiEndpoints {
    @GET("hello")
    Call<String> hello();

    @GET("category/get_all_categories")
    Call<List<Category>> getAllCategories();

    @GET("category/get_products_by_category/{categoryId}")
    Call<List<Product>> getAllProducts(@Path("categoryId") String categoryId);


    @GET("account/login")
    Call<User> login(@Query("username") String username, @Query("password") String password);

    @GET("category/get_product_details_by_productId/{productId}")
    Call<List<ProductDetail>> getProductDetail(@Path("productId") String productId);
}
