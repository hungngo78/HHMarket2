package com.hhmarket.mobile.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hhmarket.mobile.HHMarketApp;
import com.hhmarket.mobile.databinding.FragmentReviewListBinding;
import com.hhmarket.mobile.di.ComponentInjector;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.Review;
import com.hhmarket.mobile.ui.activity.ReviewActivity;
import com.hhmarket.mobile.ui.adapter.ReviewListAdapter;
import com.hhmarket.mobile.ui.viewmodel.ReviewListViewModel;
import com.hhmarket.mobile.ui.viewmodel.ReviewListViewModelFactory;
import com.hhmarket.mobile.utils.HHMarketConstants;

import java.util.List;

public class ReviewListFragment extends Fragment {

    private ReviewListViewModel mViewModel;
    private ReviewListAdapter mAdapter;

    private FragmentReviewListBinding mBinding;

    private Product mProduct;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(HHMarketConstants.TAG_REVIEWS);

        mProduct = (Product) getArguments().getParcelable(HHMarketConstants.KEY_PRODUCT);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentReviewListBinding.inflate(inflater, container, false);

        mBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewActivity currentActivity = (ReviewActivity)getActivity();
                if (((HHMarketApp)currentActivity.getApplication()).getUserId() != -1)
                    currentActivity.changePage(1);
                else
                    Toast.makeText(currentActivity, "Please log in first !", Toast.LENGTH_SHORT).show();
            }
        });

        // if user logged in already, visible "See Review" button
        ReviewActivity currentActivity = (ReviewActivity) getActivity();
        if (((HHMarketApp)currentActivity.getApplication()).getUserId() != -1) {
            mBinding.button.setVisibility(View.VISIBLE);
        }
        else {
            mBinding.button.setVisibility(View.GONE);
        }

        // adapter
        mAdapter = new ReviewListAdapter();
        mBinding.reviewList.setAdapter(mAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBinding.reviewOutline1.setProduct(mProduct);

        /* Review List */
        ReviewListViewModelFactory factory = new ReviewListViewModelFactory(
                getActivity().getApplication(), mProduct.getProductId().toString());
        mViewModel = ViewModelProviders.of(this, factory).get(ReviewListViewModel.class);

        ComponentInjector.magicBox.injectIntoReviewListViewModel(mViewModel);

        mViewModel.getReviewsfromAPI();
        subscribeUi();
    }

    // allow ReviewActivity request for updating date
    public void updateReviewList() {
        mViewModel.getReviewsfromAPI();
    }

    private void subscribeUi() {
        mViewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(@Nullable List<Review> _reviews) {
                if (_reviews != null) {
                    mBinding.setIsLoading(false);
                    mAdapter.setReviewList(_reviews);
                } else {
                    mBinding.setIsLoading(true);
                }

                // does not know how to wait for data binding's loop so we execute changes sync.
                mBinding.executePendingBindings();
            }
        });
    }
}
