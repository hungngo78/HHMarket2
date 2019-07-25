package com.hhmarket.mobile.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hhmarket.mobile.HHMarketApp;
import com.hhmarket.mobile.R;
import com.hhmarket.mobile.databinding.FragmentShoppingCartBinding;
import com.hhmarket.mobile.di.ComponentInjector;
import com.hhmarket.mobile.di.Order;
import com.hhmarket.mobile.model.CartItem;
import com.hhmarket.mobile.model.CartItemDetail;
import com.hhmarket.mobile.model.ClickListener;
import com.hhmarket.mobile.model.ClickListenerOnAdapter;
import com.hhmarket.mobile.ui.adapter.ShoppingCartAdapter;
import com.hhmarket.mobile.ui.viewmodel.ShoppingCartModel;
import com.hhmarket.mobile.ui.viewmodel.ShoppingCartViewModelFactory;
import com.hhmarket.mobile.utils.HHMarketConstants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.HEAD;

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
        ShoppingCartViewModelFactory shoppingCartViewModelFactory = new ShoppingCartViewModelFactory(
                getActivity().getApplication(), ((HHMarketApp)getActivity().getApplication()).getLoggedUserId());

        mViewModel = ViewModelProviders.of(this,shoppingCartViewModelFactory).get(ShoppingCartModel.class);
        ComponentInjector.magicBox.inject(mViewModel);

        mBinding = FragmentShoppingCartBinding.inflate(LayoutInflater.from(container.getContext()), container, false);

        mAdapder = new ShoppingCartAdapter(getActivity(), mDeleteClickListener);
        mBinding.shoppingCartList.setAdapter(mAdapder);

        mBinding.setClickListenerOrder(mOrderClickListener);

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
                calculateTotal();

            }
        });

        mViewModel.updateQuantityShoppingCart().observe(this, new Observer<CartItem>() {
            @Override
            public void onChanged(CartItem cartItem) {
                Toast.makeText(getActivity(), "updated Quantity successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void calculateTotal(){

        List<CartItemDetail> list = mAdapder.mCartItemList;
        if (list != null) {
            float totalPrice = 0;
            int quanity =0;
            for(int i = 0 ; i< list.size(); i++) {
                CartItemDetail item = list.get(i);
                totalPrice += item.getAmount() * item.getPrice();
                quanity += item.getAmount();
            }

            mBinding.setTotalAmount(quanity);
            mBinding.setTotalPrice(totalPrice);
        } else {
            mBinding.setTotalAmount(0);
            mBinding.setTotalPrice(0);
        }

    }
    private ClickListenerOnAdapter<CartItemDetail> mDeleteClickListener = new ClickListenerOnAdapter<CartItemDetail>() {

        int mPosition;
        @Override
        public void onClick(CartItemDetail object) {
            mViewModel.updateShoppingCartItemFromAPI(object.getCartDetailsId(), 0);
            mViewModel.updateQuantityShoppingCart().observe(getActivity(), new Observer<CartItem>() {
                @Override
                public void onChanged(CartItem cartItem) {
                    
                    if(cartItem != null) {
                        Toast.makeText(getActivity(), getString(R.string.delete_successfull), Toast.LENGTH_SHORT).show();
                        mAdapder.deleteOnScreen(getPosition());
                        calculateTotal();
                    } else {
                        Toast.makeText(getActivity(), getString(R.string.delete_fail), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            
        }

        @Override
        public void setPosition(int position) {
            mPosition = position;
        }

        @Override
        public int getPosition() {
            return mPosition;
        }
    };
    private ClickListener<ShoppingCartAdapter> mOrderClickListener = new ClickListener<ShoppingCartAdapter>() {
        @Override
        public void onClick(ShoppingCartAdapter object) {
            if (mAdapder.getItemCount() > 0) {
                mViewModel.orderCartItemFromAPI();
                mViewModel.orderCartItem().observe(getActivity(), new Observer<Order>() {
                    @Override
                    public void onChanged(Order order) {
                        if (order != null && order.getOrderId() > 0) {
                            Toast.makeText(getActivity(), getString(R.string.order_successful), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(), getString(R.string.order_fail), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    };
}
