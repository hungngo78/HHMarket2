package com.hhmarket.mobile.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hhmarket.mobile.databinding.CategoryItemBinding;
import com.hhmarket.mobile.model.Category;
import com.hhmarket.mobile.model.CategoryClickListener;

import java.util.List;
import java.util.Objects;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder> {
    List<? extends Category> mCategoryList;

    @Nullable
    private final CategoryClickListener mCategoryClickListener;

    public CategoryListAdapter(@Nullable CategoryClickListener clickCallback) {
        mCategoryClickListener = clickCallback;
        setHasStableIds(true);
    }

    public void setCategoryList(final List<? extends Category> categoryList) {
        if (mCategoryList == null) {
            mCategoryList = categoryList;
            notifyItemRangeInserted(0, categoryList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mCategoryList.size();
                }

                @Override
                public int getNewListSize() {
                    return categoryList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mCategoryList.get(oldItemPosition).getCategoryId() ==
                            categoryList.get(newItemPosition).getCategoryId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Category newCategory = categoryList.get(newItemPosition);
                    Category oldCategory = mCategoryList.get(oldItemPosition);
                    return newCategory.getCategoryId() == oldCategory.getCategoryId()
                            && Objects.equals(newCategory.getDescription(), oldCategory.getDescription())
                            && Objects.equals(newCategory.getName(), oldCategory.getName())
                            && newCategory.getPicture() == oldCategory.getPicture();
                }
            });
            mCategoryList = categoryList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public CategoryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //CategoryItemBinding binding = DataBindingUtil
        //        .inflate(LayoutInflater.from(parent.getContext()), R.layout.category_item,
        //                                     parent, false);
        CategoryItemBinding binding = (CategoryItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));

        binding.setClickListener(mCategoryClickListener);
        return new CategoryListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CategoryListViewHolder holder, int position) {
        holder.binding.setCategory(mCategoryList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mCategoryList == null ? 0 : mCategoryList.size();
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(mCategoryList.get(position).getCategoryId());
    }

    static class CategoryListViewHolder extends RecyclerView.ViewHolder {
        final CategoryItemBinding binding;

        public CategoryListViewHolder(CategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
