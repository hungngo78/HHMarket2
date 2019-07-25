package com.hhmarket.mobile.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ShoppingCartViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application mApplication;

    private final int mUserId;

    public ShoppingCartViewModelFactory(@NonNull Application application, int userId) {
        mApplication = application;
        mUserId = userId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ShoppingCartModel(mApplication, mUserId);
    }
}
