package com.hhmarket.mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hhmarket.mobile.utils.HHMarketConstants;

/*
 {
        "productDetailsId": 5003,
        "color": "Navy",
        "size": "2XL",
        "picture": "image1.jpg,image2.jpg,image3.jpg",
        "price": 8,
        "amount": 20,
        "productId": 1001,
        "productionName": "Fruit of the Loom",
        "description":  "Fruit of the Loom Big Men's Dual Defense EverSoft Crew Sweatshirt"
    },
 */

public class ProductDetail {


    @SerializedName("productDetailsId")
    @Expose
    private Integer productDetailsId;

    @SerializedName("productId")
    @Expose
    private Integer productId;

    @SerializedName("productionName")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("price")
    @Expose
    private float price;

    @SerializedName("size")
    @Expose
    private String size;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("color")
    @Expose
    private String  color;


    @SerializedName("amount")
    @Expose
    private Integer  amount;

    private boolean isAvailable = false;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String []getImageList() {
        String[] images = picture.split(",");
        if (images.length != 0)
            for(int i = 0 ; i< images.length; i++) {
                images[i] =  HHMarketConstants.
                S3_BUCKET_URL + "Production/" + productId + "/" + color + "/" + images[i];
            }
        else {
            return new String[] {HHMarketConstants.S3_BUCKET_URL + "no_image.png"};
        }
        return images;
    }

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

    public Integer getAmount() {
        return amount;
    }

    public void setMinPrice(Integer amount) {
        this.amount = amount;
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

    public String getColor() {
        return color;
    }

    public void setColor(String picture) {
        this.color = color;
    }

    public float gePrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getProductDetailsId() {
        return productDetailsId;
    }
    public void setProductDetailsId(Integer productDetailsId) {
        this.productDetailsId = productDetailsId;
    }

}
