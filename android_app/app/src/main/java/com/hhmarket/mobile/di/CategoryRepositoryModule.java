package com.hhmarket.mobile.di;

import javax.inject.Singleton;

import com.hhmarket.mobile.api.ApiEndpoints;

import com.hhmarket.mobile.api.repository.CategoryAPIRepository;
import com.hhmarket.mobile.api.repository.CategoryAPIRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryRepositoryModule {
    @Provides
    @Singleton
    public CategoryAPIRepository providePostRepository(ApiEndpoints apiService) {
        return new CategoryAPIRepositoryImpl(apiService);
    }
}
