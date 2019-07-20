/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hhmarket.mobile.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.hhmarket.mobile.R;

import com.hhmarket.mobile.db.entity.UserEntity;
import com.hhmarket.mobile.di.ComponentInjector;
import com.hhmarket.mobile.di.LoginInjector;
import com.hhmarket.mobile.model.Category;
import com.hhmarket.mobile.model.Product;
import com.hhmarket.mobile.model.User;
import com.hhmarket.mobile.ui.activity.ui.login.LoginActivity;
import com.hhmarket.mobile.ui.fragment.CategoryListFragment;
import com.hhmarket.mobile.ui.fragment.ProductDetailFragment;
import com.hhmarket.mobile.ui.fragment.ProductListFragment;
import com.hhmarket.mobile.ui.viewmodel.LoginViewModel;
import com.hhmarket.mobile.ui.viewmodel.LoginViewModelFactory;
import com.hhmarket.mobile.utils.HHMarketConstants;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private View navHeader;
    public LoginViewModel loginViewModel;
    public final static int REQUEST_LOGIN = 1000;
    private MenuItem menuItem_signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        menuItem_signin = (MenuItem) navigationView.getMenu().findItem(R.id.sign_in);

        // Loading profile image
        navHeader = navigationView.getHeaderView(0);
        ImageView profileImage = navHeader.findViewById(R.id.profileImage);
        Glide.with(this).load(HHMarketConstants.PROFILE_URL)
                .apply(RequestOptions.circleCropTransform())
                .thumbnail(0.5f)
                .into(profileImage);
        //Loading backgrounf image
        //ImageView navBackground = navHeader.findViewById(R.id.img_header_bg);
        //Glide.with(this).load(HHMarketConstants.BACKGROUND_URL)
        //        .thumbnail(0.5f)
        //        .into(navBackground);


        //Select Home by default
        LoginViewModelFactory mViewModelFactory = LoginInjector.provideViewModelFactory(this);
        loginViewModel = ViewModelProviders.of(this, mViewModelFactory).get(LoginViewModel.class);
        // allow inject repository into LoginViewModel
        ComponentInjector.magicBox.injectIntoLogin(loginViewModel);

        navigationView.setCheckedItem(R.id.nav_home);
        Fragment fragment = new CategoryListFragment();
        displaySelectedFragment(fragment);

        checkLogin();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN && resultCode == Activity.RESULT_OK){ //login successfull
            User result = (User)data.getSerializableExtra("result");

            menuItem_signin.setTitle(result.getFullname());
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            Fragment fragment = new CategoryListFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_shopping_cart) {
            Toast.makeText(this, "Shopping Cart", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_purchased_history) {
            Toast.makeText(this, "Purchased History", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_user_profile) {
            Toast.makeText(this, "User Profile", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_change_password) {
            Toast.makeText(this, "Change Password", Toast.LENGTH_SHORT).show();
        }

        else if (id == R.id.nav_setting) {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
            // start setting activity
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
        }
        else if (id == R.id.nav_about) {
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AboutActivity.class);
            this.startActivity(intent);
        }
        else if (id == R.id.nav_sign_out) {


            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.logout_title))
                    .setMessage(getResources().getString(R.string.logout_message))
                    .setPositiveButton(getResources().getString(R.string.logout_yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            loginViewModel.deleteAllUser();
                            menuItem_signin.setTitle(R.string.menu_sign_in);
                            Toast.makeText(getApplicationContext(), "Sign out", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton(getResources().getString(R.string.logout_no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
        else if (id == R.id.sign_in) {
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivityForResult(intent, REQUEST_LOGIN);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void checkLogin() {
        loginViewModel.getUserFromDataBase().observe(this, new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if(userEntity!= null) {
                    User user = new User();
                    user.lastName = userEntity.getLastName();
                    user.firstName = userEntity.getFirstName();
                    menuItem_signin.setTitle(user.getFullname());
                } else {
                    menuItem_signin.setTitle(R.string.menu_sign_in);
                }

            }
        });
    }
    /**
     * Loads the specified fragment to the frame
     *
     * @param fragment
     */
    private void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    /** Shows the  product list fragment */
    public void showProductList(Category category) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putString(HHMarketConstants.KEY_CATEGORY_ID, category.getCategoryId());
        fragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("product")
                .replace(R.id.frame, fragment, null).commit();
    }
    /*
    44. Difference between adding/replacing fragment in backstack?
•	replace removes the existing fragment and adds a new fragment. This means when you press back button the fragment that got replaced will be created with its onCreateView being invoked.
•	add retains the existing fragments and adds a new fragment that means existing fragment will be active and they wont be in ‘paused’ state hence when a back button is pressed onCreateView is not called for the existing fragment(the fragment which was there before new fragment was added).
•	In terms of fragment’s life cycle events onPause, onResume, onCreateView and other life cycle events will be invoked in case of replace but they wont be invoked in case of add.

     */

    public void showProductDetail(Product product) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(HHMarketConstants.KEY_PRODUCT_ID, product.getProductId().toString());
        bundle.putFloat(HHMarketConstants.KEY_STRING_DATA, product.getOverrallRating());
        bundle.putParcelable("product", product);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().addToBackStack("productDetail")
                .replace(R.id.frame, fragment, null).commit();
    }


    public void showReview(Product product) {
        Intent intent = new Intent(this, ReviewActivity.class);
        intent.putExtra("product", product);

        this.startActivity(intent);
    }
}