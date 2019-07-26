package com.hhmarket.mobile.ui.adapter;

import android.content.Context;
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
import com.hhmarket.mobile.ui.viewmodel.ShoppingCartModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder> {
    private Context context;

    // viewmodel, used to update quantity, remove cart item onto API
    private ShoppingCartModel mViewModel;

    // data set of this adapter
    public List<CartItemDetail> mCartItemList;

    // quantity combobox
    private Spinner mSpinner;
    private ArrayList<String> arr = new ArrayList<String>(Arrays.asList(new String[]{"1","2","3","4","5","6","7","8","9","10"}));


    public ShoppingCartAdapter(Context context){
        this.context = context;
    }

    public void setViewModel(ShoppingCartModel viewModel) {
        mViewModel = viewModel;
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

        /* delete cart item */
        holder.binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update onto server
                mViewModel.removeShoppingCartItemOntoAPI(cartItem.getCartDetailsId());

                // remove in data set mCartItemList
                mCartItemList.remove(positionItem);

                /* Notify all observers (recycler view) to update UI */
                // Notify any registered observers (recycler view) that the item previously located at this position has been removed
                notifyItemRemoved(positionItem);
                // Notify any registered observers that the data set has been changed
                notifyDataSetChanged();
            }
        });

        /* quantity combobox */
        // prepare adapter
        ArrayAdapter adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        populateAmountSpinner(cartItem, holder.binding, adapter);

        // handle user change quantity
        CartItemDetail currentCartItemDetail = holder.binding.getCartItem();
        mSpinner = holder.binding.amount;
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int changedQuantity = Integer.parseInt(parent.getItemAtPosition(position).toString());
                if (changedQuantity != currentCartItemDetail.getAmount()) {
                    mViewModel.updateShoppingCartItemOntoAPI(currentCartItemDetail.getCartDetailsId(), changedQuantity);

                    // update quantity of current item in mCartItemList
                    mCartItemList.get(positionItem).setAmount(changedQuantity);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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

    private void populateAmountSpinner(CartItemDetail currentItem, ShoppingCartItemBinding binding, ArrayAdapter adapter){
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
    }
}
