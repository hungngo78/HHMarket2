package com.hhmarket.mobile.di;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.api.repository.ProductAPIRepository;
import com.hhmarket.mobile.api.repository.ProductAPIRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductRepositoryModule {
    @Provides
    @Singleton
    public ProductAPIRepository provideGetRepository(ApiEndpoints apiService) {
        return new ProductAPIRepositoryImpl(apiService);
    }
}
