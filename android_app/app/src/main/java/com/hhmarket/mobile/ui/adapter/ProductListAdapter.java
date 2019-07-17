package com.hhmarket.mobile.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hhmarket.mobile.databinding.ProductItemBinding;
import com.hhmarket.mobile.model.CategoryClickListener;
import com.hhmarket.mobile.model.ClickListener;
import com.hhmarket.mobile.model.Product;

import java.util.List;
import java.util.Objects;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {
    List<? extends Product> mProductList;
    @Nullable
    private final ClickListener mClickListener;

    public ProductListAdapter(@Nullable ClickListener clickCallback) {
        mClickListener = clickCallback;
        setHasStableIds(true);

    }



    public void setProductList(final List<? extends Product> productList) {
        if (mProductList == null) {
            mProductList = productList;
            notifyItemRangeInserted(0, productList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mProductList.size();
                }

                @Override
                public int getNewListSize() {
                    return productList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mProductList.get(oldItemPosition).getProductId() ==
                            productList.get(newItemPosition).getProductId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Product newProduct = productList.get(newItemPosition);
                    Product oldProduct = mProductList.get(oldItemPosition);
                    return newProduct.getProductId() == oldProduct.getProductId()
                            && Objects.equals(newProduct.getDescription(), oldProduct.getDescription())
                            && Objects.equals(newProduct.getName(), oldProduct.getName())
                            && newProduct.getPicture() == oldProduct.getPicture()
                            && newProduct.getColor() == oldProduct.getColor()
                            && newProduct.getMinPrice() == oldProduct.getMinPrice()
                            && newProduct.getMaxPrice() == oldProduct.getMaxPrice()
                            && newProduct.getReviewNumber() == oldProduct.getReviewNumber();
                }
            });
            mProductList = productList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ProductListAdapter.ProductListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductItemBinding binding = (ProductItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
        binding.setClickListener(mClickListener);
        return new ProductListAdapter.ProductListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProductListAdapter.ProductListViewHolder holder, int position) {
        Product product = mProductList.get(position);
        holder.binding.setProduct(product);

        boolean hasDetails = true;
        if ( Float.parseFloat(product.getMinPrice()) == 0 || Float.parseFloat(product.getMaxPrice()) == 0 )
            hasDetails = false;
        holder.binding.setHasDetails(hasDetails);

        boolean hasMultiPrices = true;
        if ( Float.parseFloat(product.getMinPrice()) == Float.parseFloat(product.getMaxPrice()) )
            hasMultiPrices = false;
        holder.binding.setHasMultiPrices(hasMultiPrices);

        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mProductList == null ? 0 : mProductList.size();
    }

    @Override
    public long getItemId(int position) {
        return mProductList.get(position).getProductId();
    }



    static class ProductListViewHolder extends RecyclerView.ViewHolder {
        final ProductItemBinding binding;

        public ProductListViewHolder(ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
