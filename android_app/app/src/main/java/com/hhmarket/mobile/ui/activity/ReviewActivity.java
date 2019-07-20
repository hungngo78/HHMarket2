package com.hhmarket.mobile.ui.activity;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.hhmarket.mobile.R;
import com.hhmarket.mobile.model.OnFragmentSelectedListener;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.ui.adapter.ReviewFragmentPagerAdapter;
import com.hhmarket.mobile.ui.fragment.ReviewListFragment;
import com.hhmarket.mobile.utils.HHMarketConstants;

public class ReviewActivity extends AppCompatActivity {

    private ActionBar actionBar;

    private ViewPager vpPager;
    private ReviewFragmentPagerAdapter adapterViewPager;

    private final OnFragmentSelectedListener fragment1SelectedListener = new OnFragmentSelectedListener() {
        @Override
        public void onSelected(Fragment fragment) {
            actionBar.setTitle(HHMarketConstants.TAG_REVIEWS);
            ((ReviewListFragment) fragment).updateReviewList();
        }
    };

    private final OnFragmentSelectedListener fragment2SelectedListener = new OnFragmentSelectedListener() {
        @Override
        public void onSelected(Fragment fragment) {
            actionBar.setTitle(HHMarketConstants.TAG_REVIEW_ADDING);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setTitle("Reviews");
        }

        // receiving our product from Main Activity
        Product product = (Product) getIntent().getParcelableExtra("product");
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new ReviewFragmentPagerAdapter(fragment1SelectedListener, fragment2SelectedListener, getSupportFragmentManager(), product);
        vpPager.setAdapter(adapterViewPager);

        // when slide to page 0, reload Review List
        vpPager.addOnPageChangeListener(adapterViewPager);
    }

    // change page after submit review
    public void changePage(int page) {
        if (page == 0 || page == 1)
            vpPager.setCurrentItem(page);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
