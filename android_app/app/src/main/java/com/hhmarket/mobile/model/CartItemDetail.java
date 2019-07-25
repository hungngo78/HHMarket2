package com.hhmarket.mobile.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hhmarket.mobile.utils.HHMarketConstants;

public class CartItemDetail {

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

    @SerializedName("cartId")
    @NonNull
    @Expose
    private int cartId;


    @SerializedName("amount")
    @Expose
    private int amount;

    @SerializedName("extendedPrice")
    @Expose
    private float extendedPrice;

    @SerializedName("type")
    @Expose
    private int type;

    @SerializedName("productId")
    @Expose
    private int productId;

    @SerializedName("productDetailsId")
    @Expose
    private int productDetailsId;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("size")
    @Expose
    private String size;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("price")
    @Expose
    private float price;

    @SerializedName("totalAmountProduction")
    @Expose
    private int totalAmountProduction;

    @SerializedName("productName")
    @Expose
    private String name;

    public int getTotalAmountProduction() {
        return totalAmountProduction;
    }

    public void setTotalAmountProduction(int totalAmountProduction) {
        this.totalAmountProduction = totalAmountProduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        //https://hungngobucket1.s3-us-west-1.amazonaws.com/Production/1000/Black+Sunflower/image1.jpg
        String[] images = picture.split(",");
        if (images.length != 0)
            return HHMarketConstants.S3_BUCKET_URL + "Production/" + productId + "/" + color + "/" + images[0];
        else
            return HHMarketConstants.S3_BUCKET_URL + "no_image.png";
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductDetailsId() {
        return productDetailsId;
    }

    public void setProductDetailsId(int productDetailsId) {
        this.productDetailsId = productDetailsId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }



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
