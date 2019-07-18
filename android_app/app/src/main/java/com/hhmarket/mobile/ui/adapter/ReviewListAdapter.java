package com.hhmarket.mobile.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hhmarket.mobile.databinding.ProductItemBinding;
import com.hhmarket.mobile.databinding.ReviewItemBinding;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.Review;

import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewListViewHolder> {
    List<? extends Review> mReviewList;


    public void setReviewList(final List<? extends Review> reviewList) {
        if (mReviewList == null) {
            mReviewList = reviewList;
            notifyItemRangeInserted(0, reviewList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mReviewList.size();
                }

                @Override
                public int getNewListSize() {
                    return reviewList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mReviewList.get(oldItemPosition).getProductId() ==
                            reviewList.get(newItemPosition).getProductId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Review newReview = reviewList.get(newItemPosition);
                    Review oldReview = mReviewList.get(oldItemPosition);
                    try {
                        Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(newReview.getReviewDate());
                        Date oldDate = new SimpleDateFormat("dd/MM/yyyy").parse(oldReview.getReviewDate());

                        return newReview.getProductId() == oldReview.getProductId()
                                && newReview.getReviewId() == oldReview.getReviewId()
                                && newReview.getUserName().equals(oldReview.getUserName())
                                && newReview.getTitle().equals(oldReview.getTitle())
                                && newReview.getContent().equals(oldReview.getContent())
                                && newReview.getOverallRating() == oldReview.getOverallRating()
                                && newDate.equals(oldDate);
                    } catch (ParseException ex) {
                        return false;
                    }
                }
            });
            mReviewList = reviewList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ReviewListAdapter.ReviewListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ReviewItemBinding binding = (ReviewItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));

        return new ReviewListAdapter.ReviewListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ReviewListAdapter.ReviewListViewHolder holder, int position) {
        Review review = mReviewList.get(position);
        holder.binding.setReview(review);

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mReviewList == null ? 0 : mReviewList.size();
    }

    @Override
    public long getItemId(int position) {
        return mReviewList.get(position).getReviewId();
    }


    static class ReviewListViewHolder extends RecyclerView.ViewHolder {
        final ReviewItemBinding binding;

        public ReviewListViewHolder(ReviewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}