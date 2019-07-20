package com.hhmarket.mobile.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hhmarket.mobile.databinding.FragmentProductListBinding;
import com.hhmarket.mobile.di.ComponentInjector;
import com.hhmarket.mobile.model.ClickListener;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.ui.activity.MainActivity;

import com.hhmarket.mobile.ui.adapter.ProductListAdapter;
import com.hhmarket.mobile.ui.viewmodel.ProductListViewModel;
import com.hhmarket.mobile.ui.viewmodel.ProductListViewModelFactory;
import com.hhmarket.mobile.utils.HHMarketConstants;

import java.util.List;

public class ProductListFragment extends Fragment {
    private ProductListViewModel mViewModel;
    private ProductListAdapter mAdapter;
    private FragmentProductListBinding mBinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentProductListBinding.inflate(inflater, container, false);

        // adapter
        mAdapter = new ProductListAdapter(mClickListener);
        mBinding.productsList.setAdapter(mAdapter);
        getActivity().setTitle(HHMarketConstants.TAG_PRODUCTS);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // create ViewModel & allow inject repository
        ProductListViewModelFactory factory = new ProductListViewModelFactory(
                getActivity().getApplication(), getArguments().getString(HHMarketConstants.KEY_CATEGORY_ID));
        mViewModel = ViewModelProviders.of(this, factory).get(ProductListViewModel.class);

        ComponentInjector.magicBox.injectIntoProductListViewModel(mViewModel);

        mViewModel.getProductsfromAPI();

        subscribeUi();
    }

    private void subscribeUi() {
        mViewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> _products) {
                if (_products != null) {
                    mBinding.setIsLoading(false);
                    mAdapter.setProductList(_products);
                } else {
                    mBinding.setIsLoading(true);
                }
                // does not know how to wait for data binding's loop so we execute changes sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private final ClickListener<Product> mClickListener = new ClickListener<Product>() {
        @Override
        public void onClick(Product product) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).showProductDetail(product);
            }
        }
    };

}
