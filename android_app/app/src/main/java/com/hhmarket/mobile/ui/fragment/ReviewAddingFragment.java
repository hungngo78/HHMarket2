package com.hhmarket.mobile.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.hhmarket.mobile.HHMarketApp;
import com.hhmarket.mobile.R;
import com.hhmarket.mobile.api.repository.ReviewAPIRepository;
import com.hhmarket.mobile.databinding.FragmentReviewAddingBinding;
import com.hhmarket.mobile.di.ComponentInjector;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.Review;
import com.hhmarket.mobile.ui.activity.ReviewActivity;
import com.hhmarket.mobile.utils.HHMarketConstants;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewAddingFragment extends Fragment {

    private FragmentReviewAddingBinding mBinding;

    private Product mProduct;

    @Inject
    public ReviewAPIRepository mRepository;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProduct = (Product) getArguments().getParcelable(HHMarketConstants.KEY_PRODUCT);

        ComponentInjector.magicBox.inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentReviewAddingBinding.inflate(inflater, container, false);

        mBinding.includeReviewInput.setVisibility(View.GONE);

        mBinding.ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mBinding.includeReviewInput.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mBinding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //Toast.makeText(getActivity(), "Submiting " + rating, Toast.LENGTH_SHORT).show();
            }
        });

        // get UserName of logged account , and put on the nick_name TextView
        ReviewActivity currentActivity = (ReviewActivity) getActivity();
        EditText nickNameView = mBinding.includeReviewInput.findViewById(R.id.review_item_user);
        nickNameView.setText(((HHMarketApp)currentActivity.getApplication()).getLoggedUserName());

        EditText titleView = mBinding.includeReviewInput.findViewById(R.id.review_item_title);
        EditText contentView = mBinding.includeReviewInput.findViewById(R.id.review_item_content);
        mBinding.includeReviewInput.findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // submit onto Web API
                Review review = new Review();
                review.setProductId(mProduct.getProductId());
                ReviewActivity currentActivity = (ReviewActivity) getActivity();
                review.setUserId(((HHMarketApp)currentActivity.getApplication()).getLoggedUserId());
                review.setOverallRating(mBinding.ratingBar.getRating());
                review.setTitle(titleView.getText().toString());
                review.setContent(contentView.getText().toString());
                review.setReviewDate(LocalDateTime.now().toString());

                mRepository.addReview(review, mCallback);
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBinding.reviewOutline.setProduct(mProduct);
        mBinding.executePendingBindings();
    }

    Callback<Review> mCallback = new Callback<Review>() {
        @Override
        public void onResponse(Call<Review> call, Response<Review> response) {
            Toast.makeText(getActivity(), "Submitted your review successfully", Toast.LENGTH_SHORT).show();
            ((ReviewActivity)getActivity()).changePage(0);
        }

        @Override
        public void onFailure(Call<Review> call, Throwable t) {
            Toast.makeText(getActivity(), "Submitted your review fail", Toast.LENGTH_SHORT).show();
        }
    };
}
