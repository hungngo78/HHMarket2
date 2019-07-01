package com.hhmarket.mobile.di;

import javax.inject.Singleton;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.api.repository.CategoryRepository;
import com.hhmarket.mobile.api.repository.CategoryRepositoryImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class CategoryRepositoryModule {
    @Provides
    @Singleton
    public CategoryRepository providePostRepository(ApiEndpoints apiService) {
        return new CategoryRepositoryImpl(apiService);
    }
    /*public CategoryRepository providePostRepository() {
        return new CategoryRepositoryImpl();
    }*/

}
