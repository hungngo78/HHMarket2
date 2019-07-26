package com.hhmarket.mobile.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProductListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application mApplication;

    private final String mCategoryId;
    private final String mCriteria;

    public ProductListViewModelFactory(@NonNull Application application, String categoryId, String criteria) {
        mApplication = application;
        mCategoryId = categoryId;
        mCriteria = criteria;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ProductListViewModel(mApplication, mCategoryId, mCriteria);
    }
}
