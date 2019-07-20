package com.hhmarket.mobile.di;

import javax.inject.Singleton;

import com.hhmarket.mobile.ui.fragment.ReviewAddingFragment;
import com.hhmarket.mobile.ui.viewmodel.CategoryListViewModel;

import com.hhmarket.mobile.ui.viewmodel.LoginViewModel;
import com.hhmarket.mobile.ui.viewmodel.ProductDetailViewModel;
import com.hhmarket.mobile.ui.viewmodel.ProductListViewModel;
import com.hhmarket.mobile.ui.viewmodel.ReviewListViewModel;

import dagger.Component;

@Singleton
@Component (modules = { ApiModule.class, CategoryRepositoryModule.class,
        ProductRepositoryModule.class, UserRepositoryModule.class,
        ReviewRepositoryModule.class, ProductDetailRepositoryModule.class })

public interface MagicBox {
    // allow to inject into our viewmodel classes
    // method name not important
    void inject(CategoryListViewModel model);
    void injectIntoProductListViewModel(ProductListViewModel model);
    void injectIntoReviewListViewModel(ReviewListViewModel model);
    void inject(ReviewAddingFragment frag);

    void injectIntoLogin(LoginViewModel model);

    void injectIntoProductDetailModel(ProductDetailViewModel model);

}
