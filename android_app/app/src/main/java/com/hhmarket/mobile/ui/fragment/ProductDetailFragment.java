package com.hhmarket.mobile.ui.fragment;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.hhmarket.mobile.R;
import com.hhmarket.mobile.databinding.FragmentProductDetailBinding;
import com.hhmarket.mobile.di.ComponentInjector;
import com.hhmarket.mobile.model.ClickListener;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.ProductDetail;
import com.hhmarket.mobile.ui.activity.MainActivity;
import com.hhmarket.mobile.ui.adapter.ImageViewPagerAdapter;
import com.hhmarket.mobile.ui.viewmodel.ProductDetailView;
import com.hhmarket.mobile.ui.viewmodel.ProductDetailViewModel;
import com.hhmarket.mobile.ui.viewmodel.ProductDetailViewModelFactory;
import com.hhmarket.mobile.utils.HHMarketConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ProductDetailFragment extends Fragment {

    private ProductDetailViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;
    private ProductDetailView producdetail;
    private ProductDetail currentItem;
    DialogProductFragment fragment;
    private String []arr = new String[]{"1","2","3","4","5","6","7","8","9","10"};
    private ArrayAdapter<String> adapter;
    private ImageViewPagerAdapter imageUrlAdapter;
    private Product  mProduct;
    ViewPager pager;
    CircleIndicator indicator;
    ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(HHMarketConstants.TAG_PRODUCT_DETAILS);
        // Inflate the layout for this fragment
        mProduct = (Product) getArguments().getParcelable(HHMarketConstants.KEY_PRODUCT);
        mBinding = FragmentProductDetailBinding.inflate(inflater, container, false);
        mBinding.setClickListenerColor(clickListenerColor);
        mBinding.setClickListenerSize(clickListenerSize);
        mBinding.setOverallRating( mProduct.getReviewNumber());
        mBinding.setClickListenerReview(mProductClickListener);
        mBinding.setRatingNumber(mProduct.getReviewNumber());

        ArrayList<String> lst = new ArrayList<String>(Arrays.asList(arr));
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, lst);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pager = (ViewPager) mBinding.getRoot().getRootView().findViewById(R.id.item_image);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProductDetailViewModelFactory factory = new ProductDetailViewModelFactory(
                getActivity().getApplication(), mProduct.getProductId().toString());
        mViewModel = ViewModelProviders.of(this, factory).get(ProductDetailViewModel.class);
        ComponentInjector.magicBox.inject(mViewModel);
        mViewModel.getProductDetailfromAPI();
        subscribeUi();
    }

    public void setViewPagerAdaper() {
        if (imageUrlAdapter != null) {
            imageUrlAdapter.setImageList(currentItem.getImageList());
        } else
            imageUrlAdapter = new ImageViewPagerAdapter(getContext(),currentItem.getImageList());

        pager.setAdapter(imageUrlAdapter);
        //mBinding.setImageAdapter(imageUrlAdapter);
        indicator = (CircleIndicator) mBinding.getRoot().getRootView().findViewById(R.id.circle);
        indicator.setViewPager(pager);
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
                    setViewPagerAdaper();
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
        //fragment.setDataDisplay(producdetail.getProductDetailSizeAdapter(), currentItem.getSize(), producdetail.getProductDetails());
        fragment.setDataDisplay(producdetail.getProductDetailColorAdapter(), producdetail.getProductDetailSizeAdapter(), currentItem.getSize(), producdetail.getProductDetails(), false);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        fragment.show(ft, DialogProductFragment.TAG);
    }

    private void showFullScreenSizeDialog() {
        fragment = new DialogProductFragment(clickListener);
        Bundle args = new Bundle();
        args.putString(HHMarketConstants.KEY_TITLE_DIALOG, getString(R.string.title_dialog_size));
        fragment.setArguments(args);
        //fragment.setDataDisplay(producdetail.getProductDetailSizeAdapter(), currentItem.getSize(), producdetail.getProductDetails());
        fragment.setDataDisplay(producdetail.getProductDetailSizeAdapter(), producdetail.getProductDetailColorAdapter(), currentItem.getColor(), producdetail.getProductDetails(), true);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        fragment.show(ft, DialogProductFragment.TAG);
    }


    private ClickListener<ProductDetail> clickListener = new ClickListener<ProductDetail>() {
        @Override
        public void onClick(ProductDetail object) {
            // update current information
            fragment.dismiss();
            currentItem = object;
            mBinding.setProductDetail(object);
            createAmountSpinner();
            setViewPagerAdaper();
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

    private final ClickListener<ProductDetail> mProductClickListener = new ClickListener<ProductDetail>() {
        @Override
        public void onClick(ProductDetail product) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                MainActivity currentActivity = (MainActivity)getActivity();
                currentActivity.showReview(mProduct);
            }
        }
    };


//    @Nullable
//    private final ProductClickListener mProductClickListener;
//
//    public ProductListAdapter(@Nullable ProductClickListener clickCallback) {
//        mProductClickListener = clickCallback;
//        setHasStableIds(true);
//    }

}
