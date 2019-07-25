package com.hhmarket.mobile.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.hhmarket.mobile.HHMarketApp;
import com.hhmarket.mobile.R;
import com.hhmarket.mobile.databinding.FragmentShoppingCartBinding;
import com.hhmarket.mobile.di.ComponentInjector;
import com.hhmarket.mobile.model.CartItem;
import com.hhmarket.mobile.model.CartItemDetail;
import com.hhmarket.mobile.model.ClickListener;
import com.hhmarket.mobile.ui.adapter.ShoppingCartAdapter;
import com.hhmarket.mobile.ui.viewmodel.ShoppingCartModel;
import com.hhmarket.mobile.ui.viewmodel.ShoppingCartViewModelFactory;
import com.hhmarket.mobile.utils.HHMarketConstants;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartListFragment extends Fragment {

    private FragmentShoppingCartBinding mBinding;
    private ShoppingCartModel mViewModel;
    private ShoppingCartAdapter mAdapder;

    public ShoppingCartListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(HHMarketConstants.TAG_SHOPPING_CART);

        mBinding = FragmentShoppingCartBinding.inflate(LayoutInflater.from(container.getContext()), container, false);

        mAdapder = new ShoppingCartAdapter(getActivity(), clickListenerDelete);
        mBinding.shoppingCartList.setAdapter(mAdapder);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ShoppingCartViewModelFactory shoppingCartViewModelFactory = new ShoppingCartViewModelFactory(
                getActivity().getApplication(), ((HHMarketApp)getActivity().getApplication()).getLoggedUserId());

        mViewModel = ViewModelProviders.of(this,shoppingCartViewModelFactory).get(ShoppingCartModel.class);
        ComponentInjector.magicBox.inject(mViewModel);

        mAdapder.setViewModel(mViewModel);

        mViewModel.getShoppingCartItemListFromAPI();

        subcribe();
    }

    public void subcribe() {

        mViewModel.getShoppingCartItemList().observe(this, new Observer<List<CartItemDetail>>() {
            @Override
            public void onChanged(List<CartItemDetail> cartItems) {

                if (cartItems == null) cartItems = new ArrayList<CartItemDetail>();
                mAdapder.setShoppingCartItem(cartItems);
                mBinding.setIsLoading(false);

            }
        });

        mViewModel.updateQuantityShoppingCart().observe(this, new Observer<CartItem>() {
            @Override
            public void onChanged(CartItem cartItem) {
                Toast.makeText(getActivity(), "updated Quantity successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ClickListener<CartItemDetail> clickListenerDelete = new ClickListener<CartItemDetail>(){

        @Override
        public void onClick(CartItemDetail object) {

        }
    };
}
