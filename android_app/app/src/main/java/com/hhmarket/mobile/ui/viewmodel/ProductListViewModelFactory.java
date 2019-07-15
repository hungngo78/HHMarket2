package com.hhmarket.mobile.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hhmarket.mobile.db.repository.DataRepository;

public class ProductListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application mApplication;

    private final String mCategoryId;

    public ProductListViewModelFactory(@NonNull Application application, String categoryId) {
        mApplication = application;
        mCategoryId = categoryId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ProductListViewModel(mApplication, mCategoryId);
    }
}
