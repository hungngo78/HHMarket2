package com.hhmarket.mobile.ui.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hhmarket.mobile.R;
import com.hhmarket.mobile.databinding.ProductDetailFragmentBinding;
import com.hhmarket.mobile.di.ComponentInjector;
import com.hhmarket.mobile.model.ClickListener;
import com.hhmarket.mobile.model.ProductDetail;
import com.hhmarket.mobile.ui.adapter.ProductDetailColorListAdapter;
import com.hhmarket.mobile.ui.viewmodel.ProductDetailView;
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = ProductDetailFragmentBinding.inflate(inflater, container, false);
        mBinding.setClickListenerColor(clickListenerColor);
        mBinding.setClickListenerSize(clickListenerSize);
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
                    ProductDetailView p = new ProductDetailView(_productDetail);
                    p.getProductDetailColorAdapter();
                    p.getProductDetailSizeAdapter();
                    mBinding.setIsLoading(false);
                } else {
                    mBinding.setIsLoading(true);
                }
                // does not know how to wait for data binding's loop so we execute changes sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private void showColorDialog(){
        Dialog dialog = new Dialog(this.getActivity());
        dialog.setContentView(android.R.layout.list_content);
        ListView lv = (ListView ) dialog.findViewById(android.R.id.list);
        //lv.setAdapter();
        ProductDetailColorListAdapter adapter = new ProductDetailColorListAdapter(clickListener);
        dialog.setCancelable(true);
        dialog.setTitle("Select Color");
        dialog.show();
    }
    private void showSizeDialog(){
        Dialog dialog = new Dialog(this.getActivity());
        dialog.setContentView(android.R.layout.list_content);
        ListView lv = (ListView ) dialog.findViewById(android.R.id.list);
        //lv.setAdapter();
        ProductDetailColorListAdapter adapter = new ProductDetailColorListAdapter(clickListener);
        dialog.setCancelable(true);
        dialog.setTitle("Select Size");
        dialog.show();
    }

    private ClickListener<ProductDetail> clickListener = new ClickListener<ProductDetail>() {
        @Override
        public void onClick(ProductDetail object) {
            // choose item color
        }
    };
    private ClickListener<ProductDetail> clickListenerSize = new ClickListener<ProductDetail>() {
        @Override
        public void onClick(ProductDetail object) {
            // choose item size

            showSizeDialog();
        }
    };
    private ClickListener<ProductDetail> clickListenerColor = new ClickListener<ProductDetail>() {
        @Override
        public void onClick(ProductDetail object) {
            // choose item color
            showColorDialog();
            Intent i ;
        }
    };


}
