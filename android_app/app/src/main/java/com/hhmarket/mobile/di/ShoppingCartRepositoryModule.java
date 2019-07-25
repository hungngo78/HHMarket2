package com.hhmarket.mobile.di;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.api.repository.ShoppingCartAPIRepository;
import com.hhmarket.mobile.api.repository.ShoppingCartAPIRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ShoppingCartRepositoryModule {

    @Provides
    @Singleton
    public ShoppingCartAPIRepository providePostRepository(ApiEndpoints apiEndpoints){
        return new ShoppingCartAPIRepositoryImpl(apiEndpoints);
    }
}
