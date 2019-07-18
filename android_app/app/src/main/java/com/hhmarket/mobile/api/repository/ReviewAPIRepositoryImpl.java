package com.hhmarket.mobile.api.repository;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.model.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ReviewAPIRepositoryImpl implements ReviewAPIRepository {

    private ApiEndpoints apiService;

    public  ReviewAPIRepositoryImpl(ApiEndpoints _apiService) {
        this.apiService = _apiService;
    }

    @Override
    public void getReviews(String productId, Callback<List<Review>> callback) {
        Call<List<Review>> call = apiService.getAllReviews(productId);
        call.enqueue(callback);
    }

    public void addReview(Review review, Callback<Review> callback) {
        Call<Review> call = apiService.postReview(review);
        call.enqueue(callback);
    }
}
