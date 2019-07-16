package com.hhmarket.mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hhmarket.mobile.utils.HHMarketConstants;

public class Category {
    @SerializedName("categoryId")
    @Expose
    private String categoryId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("description")
    @Expose
    private String description;

    public String getImageUrl() {
        return HHMarketConstants.S3_BUCKET_URL + "Category/" + categoryId + "/" + picture;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
