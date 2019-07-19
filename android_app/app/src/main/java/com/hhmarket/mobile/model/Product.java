package com.hhmarket.mobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hhmarket.mobile.utils.HHMarketConstants;

import java.io.Serializable;

public class Product implements Parcelable {
    public Product(Parcel parcel) {
        productId = parcel.readInt();
        name = parcel.readString();
        description = parcel.readString();
        minPrice = parcel.readString();
        maxPrice = parcel.readString();
        picture = parcel.readString();
        color = parcel.readString();
        overallRating = parcel.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productId);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(minPrice);
        dest.writeString(maxPrice);
        dest.writeString(picture);
        dest.writeString(color);
        dest.writeFloat(overallRating);
    }
/*
    {
        "productId": 1000,
        "productionName": "Samsung galaxy 16GB",
        "description": "64GB, 4GB RAM, IP68 Water and Dust Proof, Camera: 12 MP, Front: 8 MP, Fast",
        "minPrice": 8,
        "maxPrice": 20,
        "picture": "image1.jpg,image2.jpg,image3.jpg",
        "color": "Navy"
        "reviewNumber": 5,
        "overrallRating": "2.8"
    } */

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


    @SerializedName("reviewNumber")
    @Expose
    private Integer reviewNumber;

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

    public Integer getReviewNumber() {
        return reviewNumber;
    }

    public void setReviewNumber(Integer reviewNumber) {
        this.reviewNumber = reviewNumber;
    }




    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }

        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }
    };

}
