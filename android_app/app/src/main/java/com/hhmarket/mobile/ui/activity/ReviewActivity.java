package com.hhmarket.mobile.ui.activity;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;

import com.hhmarket.mobile.R;
import com.hhmarket.mobile.model.OnFragmentSelectedListener;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.ProductClickListener;
import com.hhmarket.mobile.ui.fragment.ReviewAddingFragment;
import com.hhmarket.mobile.ui.fragment.ReviewListFragment;
import com.hhmarket.mobile.utils.HHMarketConstants;

public class ReviewActivity extends AppCompatActivity {

    private ActionBar actionBar;

    private ViewPager vpPager;
    private MyPagerAdapter adapterViewPager;

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
        adapterViewPager = new MyPagerAdapter(fragment1SelectedListener, fragment2SelectedListener, getSupportFragmentManager(), product);
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

    public static class MyPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {
        //private ActionBar mActionBar;
        private OnFragmentSelectedListener mFragment1SelectedListener;
        private OnFragmentSelectedListener mFragment2SelectedListener;

        private static int NUM_ITEMS = 2;
        private Product mProduct;
        private Fragment fragment1;
        private Fragment fragment2;

        public MyPagerAdapter(OnFragmentSelectedListener fragment1SelectedListener,
                              OnFragmentSelectedListener fragment2SelectedListener,
                              FragmentManager fragmentManager, Product product) {
            super(fragmentManager);

            mFragment1SelectedListener = fragment1SelectedListener;
            mFragment2SelectedListener = fragment2SelectedListener;

            mProduct = product;

            fragment1 = new ReviewListFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putParcelable(HHMarketConstants.KEY_PRODUCT, mProduct);
            fragment1.setArguments(bundle1);

            fragment2 = new ReviewAddingFragment();
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable(HHMarketConstants.KEY_PRODUCT, mProduct);
            fragment2.setArguments(bundle2);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return fragment1;

                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return fragment2;

                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            // Fragment creation operation is heavy. If you recreate fragment the all the views will be created
            //    and previous Fragment needs to be garbage collected. Therefore there will extra memory.
            //  Therefore rather than recreating the FirstPageFragment just refresh the data
            if (position == 0) {
                //mActionBar.setTitle(HHMarketConstants.TAG_REVIEWS);
                //((ReviewListFragment) fragment1).updateReviewList();
                mFragment1SelectedListener.onSelected(fragment1);
            } else {
                //mActionBar.setTitle(HHMarketConstants.TAG_REVIEW_ADDING);
                mFragment2SelectedListener.onSelected(fragment2);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }


}
