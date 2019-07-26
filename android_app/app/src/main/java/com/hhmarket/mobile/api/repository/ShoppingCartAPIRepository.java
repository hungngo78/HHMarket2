package com.hhmarket.mobile.api.repository;

import com.hhmarket.mobile.di.Order;
import com.hhmarket.mobile.model.CartItem;
import com.hhmarket.mobile.model.CartItemDetail;

import java.util.List;

import retrofit2.Callback;


public interface ShoppingCartAPIRepository {

    void getShoppingCartList(int userId, Callback<List<CartItemDetail>> callback);
    void addShoppingCartItem(int userId, String ProductDetailsId, int amount, float ExtendedPrice, int Type, Callback<CartItem> callback);
    void updateShoppingCartItem(int CartDetailsId, int amount, Callback<CartItem> callback);
    void orderList(int userId, Callback<Order> callback);
    void removeShoppingCartItem(int cart_detail_id, Callback<Integer> callback);

}
