package com.hhmarket.mobile.di;

import javax.inject.Singleton;

import com.hhmarket.mobile.ui.viewmodel.CategoryListViewModel;
import com.hhmarket.mobile.ui.viewmodel.LoginViewModel;

import dagger.Component;

@Singleton
@Component (modules = { ApiModule.class, CategoryRepositoryModule.class, UserRepositoryModule.class })
public interface MagicBox {
    // allow to inject into our viewmodel classes
    // method name not important
    void inject(CategoryListViewModel model);

    void injectIntoLogin(LoginViewModel model);
}
