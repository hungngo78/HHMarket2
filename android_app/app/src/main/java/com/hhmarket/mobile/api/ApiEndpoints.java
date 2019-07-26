package com.hhmarket.mobile.api;

import java.util.List;

import com.hhmarket.mobile.di.Order;
import com.hhmarket.mobile.model.CartItem;
import com.hhmarket.mobile.model.CartItemDetail;
import com.hhmarket.mobile.model.Category;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.ProductDetail;
import com.hhmarket.mobile.model.Review;
import com.hhmarket.mobile.model.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiEndpoints {
    @GET("hello")
    Call<String> hello();

    @GET("category/get_all_categories")
    Call<List<Category>> getAllCategories();

    @GET("category/get_products_by_category/{categoryId}")
    Call<List<Product>> getAllProducts(@Path("categoryId") String categoryId);

    @GET("category/searching")
    Call<List<Product>> searchProducts(@Query("criteria") String criteria);

    @GET("review/get_reviews_by_productId/{productId}")
    Call<List<Review>> getAllReviews(@Path("productId") String productId);

    @POST("review/add_review_item")
    @Headers({
            "Content-Type: application/json;charset=utf-8"
    })
    Call<Review> postReview(@Body Review user);

    @GET("account/login")
    Call<User> login(@Query("username") String username, @Query("password") String password);

    @GET("category/get_product_details_by_productId/{productId}")
    Call<List<ProductDetail>> getProductDetail(@Path("productId") String productId);

    /**
     *  Request body:
     *     {
     *         "UserId": 2,
     *             "ProductDetailsId": "5002",
     *             "Amount":5,
     *             "ExtendedPrice":100,
     *             "Type":0
     *     }
     */

    @POST("shopping/add_shoping_item")
    @Headers({
            "Content-Type: application/json;charset=utf-8"
    })
    Call<CartItem> addCartItem(@Body RequestBody bodyJson);

    @GET("shopping/get_cart_items/{userId}")
    Call<List<CartItemDetail>> getShoppingCartList(@Path("userId") int userId);

    @PUT("shopping/update_quantity")
    @Headers({
            "Content-Type: application/json;charset=utf-8"
    })
    //int CartDetailsId, @Body int amount
    Call<CartItem> updateQuantityCartItem(@Body RequestBody bodyJson);

    @POST("shopping/order/{userId}")
    @Headers({
            "Content-Type: application/json;charset=utf-8"
    })
    Call<Order> order(@Path("userId") int userId);


    @DELETE("shopping/remove_from_shopping_cart/{cart_details_id}")
    @Headers({
            "Content-Type: application/json;charset=utf-8"
    })
    Call<Integer> removeShoppingCartItem(@Path("cart_details_id") int cart_details_id);

}


