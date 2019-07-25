package com.hhmarket.mobile.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartItem {

    /*
     Response:
       {
           "cartDetailsId": 4,
           "amount": 5,
           "extendedPrice": 100,
           "type": 0
       }
     */
    @SerializedName("cartDetailsId")
    @NonNull
    @Expose
    private int cartDetailsId;

    @SerializedName("amount")
    @Expose
    private int amount;

    @SerializedName("extendedPrice")
    @Expose
    private float extendedPrice;

    @SerializedName("type")
    @Expose
    private int type;

    public int getCartDetailsId() {
        return cartDetailsId;
    }

    public void setCartDetailsId(int cartDetailsId) {
        this.cartDetailsId = cartDetailsId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(float extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
