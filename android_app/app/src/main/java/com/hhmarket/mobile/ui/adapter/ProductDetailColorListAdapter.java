package com.hhmarket.mobile.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hhmarket.mobile.databinding.ProductDetailListviewColorItemBinding;
import com.hhmarket.mobile.databinding.ProductItemBinding;
import com.hhmarket.mobile.model.ClickListener;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.ProductDetail;

import java.util.List;
import java.util.Objects;

public class ProductDetailColorListAdapter extends RecyclerView.Adapter<ProductDetailColorListAdapter.ProductDetailColorListViewHolder> {
    List<? extends ProductDetail> mProductList;
    @Nullable
    private final ClickListener mClickListener;

    public ProductDetailColorListAdapter(@Nullable ClickListener clickCallback) {
        mClickListener = clickCallback;
        setHasStableIds(true);

    }



    public void setProductList(final List<? extends ProductDetail> productList) {
        if (productList == null) return;
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
                    ProductDetail newProduct = productList.get(newItemPosition);
                    ProductDetail oldProduct = mProductList.get(oldItemPosition);
                    return newProduct.getProductId() == oldProduct.getProductId()
                            && Objects.equals(newProduct.getDescription(), oldProduct.getDescription())
                            && Objects.equals(newProduct.getName(), oldProduct.getName())
                            && newProduct.getPicture() == oldProduct.getPicture()
                            && newProduct.getColor() == oldProduct.getColor()
                            && newProduct.getProductDetailsId() == oldProduct.getProductDetailsId()
                            && newProduct.getAmount() == oldProduct.getAmount()
                            && newProduct.getSize() == oldProduct.getSize();
                }
            });
            mProductList = productList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ProductDetailColorListAdapter.ProductDetailColorListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductDetailListviewColorItemBinding binding = (ProductDetailListviewColorItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
        binding.setClickListener(mClickListener);
        return new ProductDetailColorListAdapter.ProductDetailColorListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProductDetailColorListAdapter.ProductDetailColorListViewHolder holder, int position) {
        ProductDetail product = mProductList.get(position);
        holder.binding.setProduct(product);
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



    static class ProductDetailColorListViewHolder extends RecyclerView.ViewHolder {
        final ProductDetailListviewColorItemBinding binding;

        public ProductDetailColorListViewHolder(ProductDetailListviewColorItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
