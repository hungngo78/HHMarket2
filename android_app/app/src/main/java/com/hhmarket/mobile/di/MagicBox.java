package com.hhmarket.mobile.di;

import javax.inject.Singleton;

import com.hhmarket.mobile.ui.fragment.ReviewAddingFragment;
import com.hhmarket.mobile.ui.viewmodel.CategoryListViewModel;

import com.hhmarket.mobile.ui.viewmodel.LoginViewModel;
import com.hhmarket.mobile.ui.viewmodel.ProductDetailViewModel;
import com.hhmarket.mobile.ui.viewmodel.ProductListViewModel;
import com.hhmarket.mobile.ui.viewmodel.ReviewListViewModel;
import com.hhmarket.mobile.ui.viewmodel.ShoppingCartModel;

import dagger.Component;

@Singleton
@Component (modules = { ApiModule.class, ProductionRepositoryModule.class,
        UserRepositoryModule.class, ReviewRepositoryModule.class, ShoppingCartRepositoryModule.class})

public interface MagicBox {
    // allow to inject into our viewmodel classes
    // method name not important
    void inject(LoginViewModel model);

    void inject(CategoryListViewModel model);
    void inject(ProductListViewModel model);
    void inject(ProductDetailViewModel model);
    void inject(ReviewListViewModel model);
    void inject(ReviewAddingFragment frag);
    void inject(ShoppingCartModel model);
}
