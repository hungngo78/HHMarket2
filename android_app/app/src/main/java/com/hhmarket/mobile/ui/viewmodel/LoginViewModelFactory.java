package com.hhmarket.mobile.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hhmarket.mobile.db.repository.UserDataSource;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private final UserDataSource mDataSource;

    public LoginViewModelFactory(UserDataSource dataSource) {
        mDataSource = dataSource;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}