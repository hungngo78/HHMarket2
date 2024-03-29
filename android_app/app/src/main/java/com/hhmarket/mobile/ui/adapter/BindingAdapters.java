package com.hhmarket.mobile.ui.adapter;

import androidx.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import com.hhmarket.mobile.utils.HHMarketConstants;

// Customer binding
// https://medium.com/androiddevelopers/android-data-binding-custom-setters-55a25a7aea47
public class BindingAdapters {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("imageFromUrl")
    public static void setImageURL(ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageDrawable(null);
        } else {
            //Glide.with(imageView.getContext()).load(HHMarketConstants.PROFILE_URL)
            Glide.with(imageView.getContext())
                    .load(url)
                    //.apply(RequestOptions.circleCropTransform())
                    //.thumbnail(0.5f)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }
    }
}