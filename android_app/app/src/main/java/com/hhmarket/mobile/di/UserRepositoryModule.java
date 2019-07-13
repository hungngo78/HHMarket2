package com.hhmarket.mobile.di;

import com.hhmarket.mobile.api.ApiEndpoints;

import com.hhmarket.mobile.api.repository.UserAPIRepository;
import com.hhmarket.mobile.api.repository.UserAPIRepositoryImpl;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserRepositoryModule {
    @Provides
    @Singleton
    public UserAPIRepository provideGetRepository(ApiEndpoints apiService) {
        return new UserAPIRepositoryImpl(apiService);
    }

}
