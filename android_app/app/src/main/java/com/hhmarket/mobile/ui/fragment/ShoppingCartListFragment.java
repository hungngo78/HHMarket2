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
import com.hhmarket.mobile.model.OnAdapterItemModifyListener;
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

    private OnAdapterItemModifyListener<CartItemDetail> mDeleteCartItemListener =
            new OnAdapterItemModifyListener<CartItemDetail>() {
                int mPosition = -1;

                @Override
                public void onModify(CartItemDetail object, int pos) {
                    // update onto server
                    mViewModel.removeShoppingCartItemOntoAPI(object.getCartDetailsId());

                    mPosition = pos;
                }

                public int getModifiedPosition() {
                    return this.mPosition;
                }
            };

    private ClickListener<ShoppingCartAdapter> mOrderClickListener = new ClickListener<ShoppingCartAdapter>() {
        @Override
        public void onClick(ShoppingCartAdapter object) {
            if (mAdapder.getItemCount() > 0 && mBinding.getTotalAmount() > 0 && mBinding.getTotalPrice() > 0) {
                mViewModel.orderCartItemOntoAPI();
            } else{
                Toast.makeText(getActivity(), "No item", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(HHMarketConstants.TAG_SHOPPING_CART);

        ShoppingCartViewModelFactory shoppingCartViewModelFactory = new ShoppingCartViewModelFactory(
                getActivity().getApplication(), ((HHMarketApp)getActivity().getApplication()).getLoggedUserId());

        // factory viewmodel
        mViewModel = ViewModelProviders.of(this,shoppingCartViewModelFactory).get(ShoppingCartModel.class);
        ComponentInjector.magicBox.inject(mViewModel);

        mBinding = FragmentShoppingCartBinding.inflate(LayoutInflater.from(container.getContext()), container, false);

        // set up adapter for recycler view
        mAdapder = new ShoppingCartAdapter(getActivity(), mDeleteCartItemListener);
        mBinding.shoppingCartList.setAdapter(mAdapder);

        // set OnClickListener for "Order" button
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

        subscribeUI();
    }

    public void subscribeUI() {
        mViewModel.getShoppingCartItemList().observe(this, new Observer<List<CartItemDetail>>() {
            @Override
            public void onChanged(List<CartItemDetail> cartItems) {
                if (cartItems == null)
                    cartItems = new ArrayList<CartItemDetail>();

                mAdapder.setShoppingCartItem(cartItems);
                mBinding.setIsLoading(false);

                // update total price, total amount
                updateTotal();
            }
        });

        mViewModel.updateQuantityShoppingCart().observe(this, new Observer<CartItem>() {
            @Override
            public void onChanged(CartItem cartItem) {
                if (cartItem==null) {
                    Toast.makeText(getActivity(), "updated Quantity fail", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), "updated Quantity successfully", Toast.LENGTH_SHORT).show();

                // update total price, total amount
                updateTotal();
            }
        });

        mViewModel.removeCardItem().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer cartDetailId) {
                if(cartDetailId != null && cartDetailId.intValue() >= 0) {
                    // ask mAdapter to remove this item in its dataset and notify to RecyclerView
                    if (mDeleteCartItemListener.getModifiedPosition() >= 0)
                        mAdapder.removeCartItemOnUI(mDeleteCartItemListener.getModifiedPosition());

                    Toast.makeText(getActivity(), getString(R.string.delete_successfull), Toast.LENGTH_SHORT).show();

                    // update total price, total amount
                    updateTotal();
                } else {
                    Toast.makeText(getActivity(), getString(R.string.delete_fail), Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private void updateTotal(){
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
}
