package com.hhmarket.mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hhmarket.mobile.utils.HHMarketConstants;

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

    @SerializedName("overrallRating")
    @Expose
    private float overallRating = 0f;

    public String getImageUrl() {
        //https://hungngobucket1.s3-us-west-1.amazonaws.com/Production/1000/Black+Sunflower/image1.jpg
        String[] images = picture.split(",");
        if (images.length != 0)
            return HHMarketConstants.S3_BUCKET_URL + "Production/" + productId + "/" + color + "/" + images[0];
        else
            return HHMarketConstants.S3_BUCKET_URL + "no_image.png";
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

    public float getOverrallRating() {
        return overallRating;
    }
    public void setOverallRating(float overallRating) {
        this.overallRating = overallRating;
    }

}
