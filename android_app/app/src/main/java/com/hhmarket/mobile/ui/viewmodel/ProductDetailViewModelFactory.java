package com.hhmarket.mobile.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProductDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application mApplication;

    private final String mProductId;

    public ProductDetailViewModelFactory(@NonNull Application application, String productId) {
        mApplication = application;
        mProductId = productId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ProductDetailViewModel(mApplication, mProductId);
    }
}
