package com.hhmarket.mobile.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import com.hhmarket.mobile.databinding.CategoryListFragmentBinding;

import com.hhmarket.mobile.model.Category;
import com.hhmarket.mobile.model.CategoryClickListener;
import com.hhmarket.mobile.ui.activity.MainActivity;
import com.hhmarket.mobile.ui.adapter.CategoryListAdapter;
import com.hhmarket.mobile.di.ComponentInjector;
import com.hhmarket.mobile.ui.viewmodel.CategoryListViewModel;
import com.hhmarket.mobile.utils.HHMarketConstants;

public class CategoryListFragment extends Fragment {
    private CategoryListViewModel mViewModel;
    private CategoryListAdapter mAdapter;
    private CategoryListFragmentBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //mBinding = DataBindingUtil.inflate(inflater, R.layout.category_list_fragment, container, false);
        mBinding = CategoryListFragmentBinding.inflate(inflater, container, false);
        getActivity().setTitle(HHMarketConstants.TAG_HOME);
        // adapter
        mAdapter = new CategoryListAdapter(mCategoryClickListener);
        mBinding.categoriesList.setAdapter(mAdapter);

        return mBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // create ViewModel & allow inject repository
        mViewModel = ViewModelProviders.of(this).get(CategoryListViewModel.class);
        if (ComponentInjector.magicBox != null) {
            Log.i("-----------------", "--------------------------------------- magix box is not NULL");
        } else {
            Log.i("-----------------", "--------------------------------------- magix box is NULL");
        }
        ComponentInjector.magicBox.inject(mViewModel);

        mViewModel.getCategoriesfromAPI();

        subscribeUi();
    }

    private void subscribeUi() {
        mViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> myCategories) {
                if (myCategories != null) {
                    mBinding.setIsLoading(false);
                    mAdapter.setCategoryList(myCategories);
                } else {
                    mBinding.setIsLoading(true);
                }
                // does not know how to wait for data binding's loop so we execute changes sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private final CategoryClickListener mCategoryClickListener = new CategoryClickListener() {
        @Override
        public void onClick(Category category) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).showProductList(category);
            }
        }
    };
}
