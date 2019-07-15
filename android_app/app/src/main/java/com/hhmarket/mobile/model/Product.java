package com.hhmarket.mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("productId")
    @Expose
    private Integer productId;

    @SerializedName("productionName")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("minPrice")
    @Expose
    private String minPrice;

    @SerializedName("maxPrice")
    @Expose
    private String maxPrice;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("color")
    @Expose
    private String  color;


    // gia lap khi chua lay hinh tu Web
    private String imageUrl;
    public String getImageUrl() {
        //return imageUrl;
        return "https://upload.wikimedia.org/wikipedia/commons/5/55/Apple_orchard_in_Tasmania.jpg";
    }
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String picture) {
        this.color = color;
    }
}
