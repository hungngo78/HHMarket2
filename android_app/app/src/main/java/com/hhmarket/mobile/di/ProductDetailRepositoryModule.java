package com.hhmarket.mobile.di;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.api.repository.ProductDetailAPIRepository;
import com.hhmarket.mobile.api.repository.ProductDetailRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductDetailRepositoryModule {
    @Provides
    @Singleton
    public ProductDetailAPIRepository provideGetRepository(ApiEndpoints apiService) {
        return new ProductDetailRepositoryImpl(apiService);
    }
}
