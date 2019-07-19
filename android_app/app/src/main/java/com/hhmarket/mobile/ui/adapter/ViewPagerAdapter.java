package com.hhmarket.mobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.hhmarket.mobile.R;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private String []imageList;
    public ViewPagerAdapter(Context context, String []imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    public void setImageList(String []imageList) {

        this.imageList = imageList;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_image_item, null);
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setTag(R.id.image, position);
        Glide
                    .with(context)
                    .load(imageList[position])
                    .into(imageView);

        container.addView(view);
        return view;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }
    /*
    Returns the count of the total pages
    */
    @Override
    public int getCount() {
        return imageList.length;
    }
    /*
    Used to determine whether the page view is associated with object key returned by instantiateItem.
    Since here view only is the key we return view==object
    */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }


}
