package com.hhmarket.mobile.ui.fragment;

import androidx.databinding.Bindable;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDetailFragment extends Fragment {

    private ProductDetailViewModel mViewModel;
    private ProductDetailFragmentBinding mBinding;
    private ProductDetailView producdetail;
    private ProductDetail currentItem;
    DialogProductFragment fragment;
    private String []arr = new String[]{"1","2","3","4","5","6","7","8","9","10"};
    private ArrayAdapter<String> adapter;
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
        mBinding.setOverallRating( getArguments().getFloat(HHMarketConstants.KEY_STRING_DATA));

        ArrayList<String> lst = new ArrayList<String>(Arrays.asList(arr));
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, lst);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

    public void createAmountSpinner(){
        if (currentItem.getAmount() == 0) {
            arr = new String[]{"0"};
        } else
        if (currentItem.getAmount().intValue() >= 10) {
            arr = new String[]{"1","2","3","4","5","6","7","8","9","10"};
        } else {
            arr = new String[currentItem.getAmount()];
            for(int i = 0; i < currentItem.getAmount(); i++) {

                arr[i] = ""+i;
            }
        }
        ArrayList<String> lst = new ArrayList<String>(Arrays.asList(arr));
        adapter.clear();
        adapter.addAll(arr);
        mBinding.setAmountAdapter(adapter);

    }

    private void subscribeUi() {
        mViewModel.getProductDetail().observe(this, new Observer<List<ProductDetail>>() {
            @Override
            public void onChanged(@Nullable List<ProductDetail> _productDetail) {
                if (_productDetail != null) {
                    producdetail  = new ProductDetailView(_productDetail);
                    currentItem = _productDetail.get(0);
                    mBinding.setProductDetail(currentItem);
                    mBinding.setIsLoading(false);
                    createAmountSpinner();
                } else {
                    mBinding.setIsLoading(true);
                }
                // does not know how to wait for data binding's loop so we execute changes sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private void showFullScreenColorDialog() {

        fragment = new DialogProductFragment(clickListener);
        Bundle args = new Bundle();
        args.putString(HHMarketConstants.KEY_TITLE_DIALOG, getString(R.string.title_dialog_color));
        fragment.setArguments(args);
        fragment.setDataDisplay(producdetail.getProductDetailSizeAdapter(), currentItem.getSize(), producdetail.getProductDetails());
        //fragment.setDataDisplay(producdetail.getProductDetailSizeAdapter(), currentItem.getSize());
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        fragment.show(ft, DialogProductFragment.TAG);

    }
    private void showFullScreenSizeDialog() {

        fragment = new DialogProductFragment(clickListener);
        Bundle args = new Bundle();
        args.putString(HHMarketConstants.KEY_TITLE_DIALOG, getString(R.string.title_dialog_size));
        fragment.setArguments(args);
        fragment.setDataDisplay(producdetail.getProductDetailSizeAdapter(), currentItem.getSize(), producdetail.getProductDetails());
        //fragment.setDataDisplay(producdetail.getProductDetailColorAdapter(), currentItem.getColor());
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        fragment.show(ft, DialogProductFragment.TAG);

    }


    private void showColorDialog(){
        Dialog dialog = new Dialog(this.getActivity());
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setLayout(width, height);
        dialog.setContentView(R.layout.dialog_productdetail_list);
        RecyclerView lv = (RecyclerView ) dialog.findViewById(R.id.products_list);
        ProductDetailColorListAdapter adapter = new ProductDetailColorListAdapter(clickListener);
        adapter.setProductList(producdetail.getProductDetailSizeAdapter().get(currentItem.getSize()));
        lv.setAdapter(adapter);
        dialog.setCancelable(true);
        dialog.setTitle("Select Size");
        dialog.show();
    }

    private ClickListener<ProductDetail> clickListener = new ClickListener<ProductDetail>() {
        @Override
        public void onClick(ProductDetail object) {
            // update current information
            fragment.dismiss();
            mBinding.setProductDetail(object);
            createAmountSpinner();
        }
    };
    private ClickListener<ProductDetail> clickListenerSize = new ClickListener<ProductDetail>() {
        @Override
        public void onClick(ProductDetail object) {
            // choose item size
            showFullScreenSizeDialog();

        }
    };
    private ClickListener<ProductDetail> clickListenerColor = new ClickListener<ProductDetail>() {
        @Override
        public void onClick(ProductDetail object) {
            // choose item color
            showFullScreenColorDialog();
        }
    };


}
