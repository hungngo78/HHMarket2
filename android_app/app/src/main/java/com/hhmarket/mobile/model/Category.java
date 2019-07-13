package com.hhmarket.mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("description")
    @Expose
    private String description;


    // gia lap khi chua lay hinh tu Web
    private String imageUrl;
    public String getImageUrl() {
        //return imageUrl;
        return "https://upload.wikimedia.org/wikipedia/commons/5/55/Apple_orchard_in_Tasmania.jpg";
    }
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
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
