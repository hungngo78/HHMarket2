package com.hhmarket.mobile.api.repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.di.Order;
import com.hhmarket.mobile.model.CartItem;
import com.hhmarket.mobile.model.CartItemDetail;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;


import java.util.List;

public class ShoppingCartAPIRepositoryImpl implements ShoppingCartAPIRepository{

    ApiEndpoints apiEndpoints;

    public ShoppingCartAPIRepositoryImpl(ApiEndpoints apiEndpoints) {
        this.apiEndpoints = apiEndpoints;

    }

    @Override
    public void addShoppingCartItem(int userId, String ProductDetailsId, int amount, float ExtendedPrice, int Type, Callback<CartItem> callback) {

        CartItem item = new CartItem();
        JSONObject json = new JSONObject();
        try {
            json.put("UserId", userId);
            json.put("ProductDetailsId", ProductDetailsId);
            json.put("Amount", amount);
            json.put("ExtendedPrice", ExtendedPrice);
            json.put("Type", Type);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestBody body =
                RequestBody.create(MediaType.parse("application/json"), json.toString());
        Call<CartItem> call = apiEndpoints.addCartItem(body);
        call.enqueue(callback);
    }

    @Override
    public void getShoppingCartList(int userId, Callback<List<CartItemDetail>> callback) {
        Call<List<CartItemDetail>> call = apiEndpoints.getShoppingCartList(userId);
        call.enqueue(callback);
    }


    @Override
    public void updateShoppingCartItem(int CartDetailsId, int amount, Callback<CartItem> callback) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("CartDetailsId", CartDetailsId);
        jsonObject.addProperty("Amount", amount);
        RequestBody body =
                RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        System.out.println("delete :" + body);
        Call<CartItem> call = apiEndpoints.updateQuantityCartItem(body);
        call.enqueue(callback);
    }

    @Override
    public void orderList(int userId, Callback<Order> callback) {

        Call<Order> call = apiEndpoints.order(userId);
        call.enqueue(callback);



    }

    @Override
    public void removeShoppingCartItem(int cart_detail_id, Callback<Integer> callback) {

        Call<Integer> call = apiEndpoints.removeShoppingCartItem(cart_detail_id);
        call.enqueue(callback);

    }
}
