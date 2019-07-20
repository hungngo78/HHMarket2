package com.hhmarket.mobile.di;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.api.repository.ReviewAPIRepositoryImpl;
import com.hhmarket.mobile.api.repository.ReviewAPIRepository;
import com.hhmarket.mobile.model.Review;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ReviewRepositoryModule {
    @Provides
    @Singleton
    public ReviewAPIRepository provideGetRepository(ApiEndpoints apiService) {
        return new ReviewAPIRepositoryImpl(apiService);
    }
}
