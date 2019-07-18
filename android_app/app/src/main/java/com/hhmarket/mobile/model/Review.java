package com.hhmarket.mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {
    /*
    "ReviewId": 4021,
    "ProductId": 11,
    "UserName": "hung"
    "Title": "AA",
    "Content": "AAAAAAAAA",
    "OverallRating": 2,
    "ReviewDate": "2019-01-13"*/

    @SerializedName("ReviewId")
    @Expose
    private Integer reviewId;

    @SerializedName("ProductId")
    @Expose
    private Integer productId;

    @SerializedName("UserId")
    @Expose
    private String userId;

    @SerializedName("UserName")
    @Expose
    private String userName;

    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("Content")
    @Expose
    private String content;

    @SerializedName("OverallRating")
    @Expose
    private float overallRating;

    @SerializedName("ReviewDate")
    @Expose
    private String reviewDate;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(float overallRating) {
        this.overallRating = overallRating;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
