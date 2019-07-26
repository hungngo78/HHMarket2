package com.hhmarket.mobile.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hhmarket.mobile.databinding.ShoppingCartItemBinding;
import com.hhmarket.mobile.model.CartItemDetail;
import com.hhmarket.mobile.model.ClickListener;
import com.hhmarket.mobile.ui.viewmodel.ShoppingCartModel;
import com.hhmarket.mobile.model.ClickListenerOnAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.http.HEAD;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder> {
    private Context context;

    private ShoppingCartModel mViewModel;

    public List<CartItemDetail> mCartItemList;

    private ClickListenerOnAdapter<CartItemDetail> mSelectClickListener;

    private Spinner mSpinner;
    private ArrayList<String> arr = new ArrayList<String>(Arrays.asList(new String[]{"1","2","3","4","5","6","7","8","9","10"}));


    public ShoppingCartAdapter(Context context, ClickListenerOnAdapter selectClickListener){
        this.context = context;
        mSelectClickListener = selectClickListener;
    }

    public void setViewModel(ShoppingCartModel viewModel) {
        mViewModel = viewModel;
    }

    public void createAmountSpinner(CartItemDetail currentItem, ShoppingCartItemBinding binding, ArrayAdapter adapter){
        if (currentItem.getTotalAmountProduction() == 0) {
            arr = new ArrayList<String>();
            arr.add("0");
        } else
        if (currentItem.getTotalAmountProduction() >= 10) {
            arr = new ArrayList<String>(Arrays.asList(new String[]{"0","1","2","3","4","5","6","7","8","9","10"}));
        } else {
            arr = new ArrayList<String>();
            for(int i = 0; i <= currentItem.getTotalAmountProduction(); i++) {

                arr.add(""+ i);
            }
        }

        adapter.clear();
        adapter.addAll(arr);

        binding.amount.setAdapter(adapter);
        binding.amount.setSelection(currentItem.getAmount(), false);

        /*binding.setAmountAdapter(adapter);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                binding.amount.setSelection(currentItem.getAmount(), false);
            }
        }, 100);*/
    }
    public void setShoppingCartItem(final List<CartItemDetail> cartItemList){

        if (this.mCartItemList == null) {
            this.mCartItemList = cartItemList;
            notifyItemRangeInserted(0, mCartItemList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mCartItemList.size();
                }

                @Override
                public int getNewListSize() {
                    return cartItemList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mCartItemList.get(oldItemPosition).getProductId() ==
                            cartItemList.get(newItemPosition).getProductId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    CartItemDetail newCartItem = cartItemList.get(newItemPosition);
                    CartItemDetail oldCartItem = mCartItemList.get(oldItemPosition);

                        return newCartItem.getProductId() == oldCartItem.getProductId()
                                && newCartItem.getCartId() == oldCartItem.getCartId()
                                && newCartItem.getCartDetailsId() == oldCartItem.getCartDetailsId()
                                && newCartItem.getName().equals(oldCartItem.getName())
                                && newCartItem.getSize().equals(oldCartItem.getSize());
                }
            });
            mCartItemList = cartItemList;
            result.dispatchUpdatesTo(this);
        }
    }


    @NonNull
    @Override
    public ShoppingCartAdapter.ShoppingCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ShoppingCartItemBinding shoppingCartItemBinding = ShoppingCartItemBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
        return new ShoppingCartViewHolder(shoppingCartItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartAdapter.ShoppingCartViewHolder holder, int positionItem) {

        CartItemDetail cartItem = mCartItemList.get(positionItem);
        holder.binding.setCartItem(cartItem);
        ArrayAdapter adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ClickListenerOnAdapter<CartItemDetail> mClickListenerDelete ;
        mClickListenerDelete = new ClickListenerOnAdapter<CartItemDetail>(){

            int mPosition = -1;
            @Override
            public void onClick(CartItemDetail object) {
                delete(getPosition());
            }
            public void setPosition(int position){
                mPosition = position;
            }

            @Override
            public int getPosition() {
                return mPosition;
            }
        };
        mClickListenerDelete.setPosition(positionItem);
        holder.binding.setPosition(positionItem);
        createAmountSpinner(cartItem, holder.binding, adapter);
        holder.binding.setClickListenerDelete(mClickListenerDelete);

        // handle user change quantity
        CartItemDetail currentCartItemDetail = holder.binding.getCartItem();
        mSpinner = holder.binding.amount;
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedItem = Integer.parseInt(parent.getItemAtPosition(position).toString());
                if (selectedItem != currentCartItemDetail.getAmount())
                    mViewModel.updateShoppingCartItemFromAPI(currentCartItemDetail.getCartDetailsId(), selectedItem);
                    mSelectClickListener.setPosition(positionItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void delete(int position) { //removes the row
        if (position < 0 || position >= mCartItemList.size()) {
            return;
        }
        // call to server
        mSelectClickListener.setPosition(position);
        mSelectClickListener.onClick(mCartItemList.get(position));
    }

    public void deleteOnScreen(int position) {
        if (position < 0 || position >= mCartItemList.size()) {
            return;
        }
        mCartItemList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mCartItemList != null? mCartItemList.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return mCartItemList != null? mCartItemList.get(position).getCartDetailsId():0;
    }

    static class ShoppingCartViewHolder extends RecyclerView.ViewHolder{
        final ShoppingCartItemBinding binding;

        public ShoppingCartViewHolder(ShoppingCartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



}
