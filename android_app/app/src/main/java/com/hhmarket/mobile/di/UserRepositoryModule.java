package com.hhmarket.mobile.di;

import com.hhmarket.mobile.api.ApiEndpoints;
import com.hhmarket.mobile.api.repository.UserRepository;
import com.hhmarket.mobile.api.repository.UserRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserRepositoryModule {
    @Provides
    @Singleton
    public UserRepository provideGetRepository(ApiEndpoints apiService) {
        return new UserRepositoryImpl(apiService);
    }


}
