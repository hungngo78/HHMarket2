package com.hhmarket.mobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hhmarket.mobile.databinding.ShoppingCartItemBinding;
import com.hhmarket.mobile.model.CartItemDetail;
import com.hhmarket.mobile.model.ClickListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder> {


    public List<CartItemDetail> mCartItemList;
    public ClickListener<CartItemDetail> mClickListenerDelete;
    private ArrayList<String> arr = new ArrayList<String>(Arrays.asList(new String[]{"1","2","3","4","5","6","7","8","9","10"}));
    private Context context ;
    public ShoppingCartAdapter(Context context, ClickListener<CartItemDetail> clickListenerDelete){
        this.context = context;
        mClickListenerDelete = clickListenerDelete;


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

        int selection = currentItem.getAmount();
        if (currentItem.getTotalAmountProduction() < currentItem.getAmount())
            selection = currentItem.getTotalAmountProduction();

        adapter.clear();
        adapter.addAll(arr);
        binding.setAmountAdapter(adapter);
        binding.amount.setSelection(selection);
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
    public void onBindViewHolder(@NonNull ShoppingCartAdapter.ShoppingCartViewHolder holder, int position) {

        CartItemDetail cartItem = mCartItemList.get(position);
        holder.binding.setCartItem(cartItem);
        ArrayAdapter adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        createAmountSpinner(cartItem, holder.binding, adapter);


    }

    @Override
    public int getItemCount() {
        return mCartItemList != null? mCartItemList.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return mCartItemList != null? mCartItemList.get(position).getCartDetailsId():0;
    }

    static class ShoppingCartViewHolder extends RecyclerView.ViewHolder {
        final ShoppingCartItemBinding binding;

        public ShoppingCartViewHolder(ShoppingCartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
