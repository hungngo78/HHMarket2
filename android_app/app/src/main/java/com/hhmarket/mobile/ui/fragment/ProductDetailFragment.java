package com.hhmarket.mobile.ui.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhmarket.mobile.R;
import com.hhmarket.mobile.databinding.ProductDetailFragmentBinding;
import com.hhmarket.mobile.di.ComponentInjector;
import com.hhmarket.mobile.model.ProductDetail;
import com.hhmarket.mobile.ui.viewmodel.ProductDetailViewModel;
import com.hhmarket.mobile.ui.viewmodel.ProductDetailViewModelFactory;
import com.hhmarket.mobile.utils.HHMarketConstants;

import java.util.List;

public class ProductDetailFragment extends Fragment {

    private ProductDetailViewModel mViewModel;
    private ProductDetailFragmentBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(HHMarketConstants.TAG_PRODUCT_DETAILS);
    }
    public static ProductDetailFragment newInstance() {
        return new ProductDetailFragment();
    }

//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.product_detail_fragment, container, false);
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = ProductDetailFragmentBinding.inflate(inflater, container, false);
       //View a =  inflater.inflate(R.layout.product_detail_fragment, container, false);
        return mBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProductDetailViewModelFactory factory = new ProductDetailViewModelFactory(
                getActivity().getApplication(), getArguments().getString(HHMarketConstants.KEY_PRODUCT_ID));
        mViewModel = ViewModelProviders.of(this, factory).get(ProductDetailViewModel.class);

        ComponentInjector.magicBox.injectIntoProductDetailModel(mViewModel);

        mViewModel.getProductDetailfromAPI();

        subscribeUi();
    }

    private void subscribeUi() {
        mViewModel.getProductDetail().observe(this, new Observer<List<ProductDetail>>() {
            @Override
            public void onChanged(@Nullable List<ProductDetail> _productDetail) {
                if (_productDetail != null) {
                    mBinding.setIsLoading(false);
                } else {
                    mBinding.setIsLoading(true);
                }
                // does not know how to wait for data binding's loop so we execute changes sync.
                mBinding.executePendingBindings();
            }
        });
    }

}
