package com.hhmarket.mobile.api.repository;

import com.hhmarket.mobile.model.Review;

import java.util.List;

import retrofit2.Callback;

public interface ReviewAPIRepository {
    public void getReviews(String productId, Callback<List<Review>> callback);

    public void addReview(Review review, Callback<Review> callback);
}
