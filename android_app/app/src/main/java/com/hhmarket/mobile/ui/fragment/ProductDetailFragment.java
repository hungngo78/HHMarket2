package com.hhmarket.mobile.ui.fragment;

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
import android.widget.Toast;

import com.hhmarket.mobile.HHMarketApp;
import com.hhmarket.mobile.R;
import com.hhmarket.mobile.databinding.FragmentProductDetailBinding;
import com.hhmarket.mobile.di.ComponentInjector;
import com.hhmarket.mobile.model.CartItem;
import com.hhmarket.mobile.model.ClickListener;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.ProductDetail;
import com.hhmarket.mobile.ui.activity.MainActivity;
import com.hhmarket.mobile.ui.adapter.ImageViewPagerAdapter;
import com.hhmarket.mobile.ui.viewmodel.ProductDetailView;
import com.hhmarket.mobile.ui.viewmodel.ProductDetailViewModel;
import com.hhmarket.mobile.ui.viewmodel.ProductDetailViewModelFactory;
import com.hhmarket.mobile.ui.viewmodel.ShoppingCartModel;
import com.hhmarket.mobile.ui.viewmodel.ShoppingCartViewModelFactory;
import com.hhmarket.mobile.utils.HHMarketConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ProductDetailFragment extends Fragment {

    private ProductDetailViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;

    // There are 2 dialogFragments used in this screen: color and size picking up dialogs
    private DialogProductFragment fragment;

    // holding data displayed in color and size picking up dialogs
    private ProductDetailView productDetailView;
    private ProductDetail currentItem;

    // quantity combobox
    private String []arr = new String[]{"1","2","3","4","5","6","7","8","9","10"};
    private ArrayAdapter<String> adapter;

    // Product object received from MainActivity
    private Product  mProduct;

    // Image viewpager, which is a slide representing all images of this product
    private ViewPager pager;
    private ImageViewPagerAdapter imageUrlAdapter;
    private CircleIndicator indicator;

    private ShoppingCartModel shoppingCartModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ShoppingCartViewModelFactory shoppingCartViewModelFactory = new ShoppingCartViewModelFactory(getActivity().getApplication(),
                ((HHMarketApp)getActivity().getApplication()).getLoggedUserId());

        shoppingCartModel = ViewModelProviders.of(this,shoppingCartViewModelFactory)
                .get(ShoppingCartModel.class);
        ComponentInjector.magicBox.inject(shoppingCartModel);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(HHMarketConstants.TAG_PRODUCT_DETAILS);

        // get Product object from MainActivity
        mProduct = (Product) getArguments().getParcelable(HHMarketConstants.KEY_PRODUCT);

        // Inflate the layout for this fragment
        mBinding = FragmentProductDetailBinding.inflate(inflater, container, false);

        mBinding.setClickListenerColor(clickListenerColor);
        mBinding.setClickListenerSize(clickListenerSize);
        mBinding.setClickListenerAddToCart(clickListenerAddToCart);
        mBinding.setOverallRating( mProduct.getReviewNumber());
        mBinding.setClickListenerReview(mProductClickListener);
        mBinding.setRatingNumber(mProduct.getReviewNumber());

        /* quantity combobox */
        ArrayList<String> lst = new ArrayList<String>(Arrays.asList(arr));
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, lst);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Image viewpager, which is a slide representing all images of this product
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

    private void subscribeUi() {
        mViewModel.getProductDetail().observe(this, new Observer<List<ProductDetail>>() {
            @Override
            public void onChanged(@Nullable List<ProductDetail> _productDetail) {
                if (_productDetail != null) {
                    // from the list of productdetails, get the first item to display
                    productDetailView  = new ProductDetailView(_productDetail);
                    currentItem = _productDetail.get(0);

                    // bind above productdetails onto UI
                    mBinding.setProductDetail(currentItem);
                    mBinding.setIsLoading(false);

                    // populate quantity combobox
                    populateAmountSpinner();

                    // setup adapter, indicator for ImageViewPager ( of current image )
                    setViewPagerAdaper();
                } else {
                    mBinding.setIsLoading(true);
                }
                // does not know how to wait for data binding's loop so we execute changes sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private void setViewPagerAdaper() {
        if (imageUrlAdapter != null) {
            imageUrlAdapter.setImageList(currentItem.getImageList());
        } else {
            imageUrlAdapter = new ImageViewPagerAdapter(getContext(), currentItem.getImageList());
        }

        pager.setAdapter(imageUrlAdapter);

        indicator = (CircleIndicator) mBinding.getRoot().getRootView().findViewById(R.id.circle);
        indicator.setViewPager(pager);
    }

    private void populateAmountSpinner(){
        if (currentItem.getAmount() == 0) {
            arr = new String[]{"0"};
        } else {
            if (currentItem.getAmount().intValue() >= 10) {
                arr = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            } else {
                arr = new String[currentItem.getAmount()];
                for (int i = 0; i < currentItem.getAmount(); i++) {

                    arr[i] = "" + i;
                }
            }
        }

        adapter.clear();
        adapter.addAll(arr);

        mBinding.setAmountAdapter(adapter);
    }

    private void showFullScreenColorDialog() {
        fragment = new DialogProductFragment(onDialogItemClickListener);

        Bundle args = new Bundle();
        args.putString(HHMarketConstants.KEY_TITLE_DIALOG, getString(R.string.title_dialog_color));
        fragment.setArguments(args);
        fragment.setDataDisplay(productDetailView.getProductDetailColorAdapter(),
                            productDetailView.getProductDetailSizeAdapter(), currentItem.getSize(),
                     false);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        fragment.show(ft, DialogProductFragment.TAG);
    }

    private void showFullScreenSizeDialog() {
        fragment = new DialogProductFragment(onDialogItemClickListener);

        Bundle args = new Bundle();
        args.putString(HHMarketConstants.KEY_TITLE_DIALOG, getString(R.string.title_dialog_size));
        fragment.setArguments(args);
        fragment.setDataDisplay(productDetailView.getProductDetailSizeAdapter(),
                        productDetailView.getProductDetailColorAdapter(), currentItem.getColor(),
                 true);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        fragment.show(ft, DialogProductFragment.TAG);
    }

    private ClickListener<ProductDetail> onDialogItemClickListener = new ClickListener<ProductDetail>() {
        @Override
        public void onClick(ProductDetail object) {
            // close dialog
            fragment.dismiss();

            /* update current information */
            // update current product details item
            currentItem = object;
            mBinding.setProductDetail(object);

            // re-populate quantity combobox
            populateAmountSpinner();

            // re-setup adapter, indicator for ImageViewPager ( of current image )
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
    private final ClickListener<ProductDetail> clickListenerAddToCart = new ClickListener<ProductDetail>() {
        @Override
        public void onClick(ProductDetail product) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                MainActivity currentActivity = (MainActivity)getActivity();
                // add to cart

                int user = ((HHMarketApp)getActivity().getApplication()).getLoggedUserId();
                if(user <= 0) {
                    Toast.makeText(getActivity(), getString(R.string.request_login), Toast.LENGTH_SHORT).show();
                    return;
                }

                String amount = (String)mBinding.amount.getSelectedItem();
                System.out.println("amount = " + amount);
                shoppingCartModel.addShoppingCartItemOntoAPI(product.getProductDetailsId().toString(),
                        Integer.parseInt(amount),product.gePrice(),0);
                shoppingCartModel.addShoppingCartItem().observe(getViewLifecycleOwner(), new Observer<CartItem>() {
                    @Override
                    public void onChanged(CartItem cartItem) {

                        if (cartItem != null) {
                            Toast.makeText(getContext(), getActivity().getString(R.string.add_cart_successfull), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    };
}
