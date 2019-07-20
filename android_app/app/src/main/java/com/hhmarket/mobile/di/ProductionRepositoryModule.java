package com.hhmarket.mobile.di;

import javax.inject.Singleton;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.api.repository.ProductionAPIRepository;
import com.hhmarket.mobile.api.repository.ProductionAPIRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryRepositoryModule {
    @Provides
    @Singleton
    public ProductionAPIRepository providePostRepository(ApiEndpoints apiService) {
        return new ProductionAPIRepositoryImpl(apiService);
    }
}
