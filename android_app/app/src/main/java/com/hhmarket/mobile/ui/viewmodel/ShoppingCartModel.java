package com.hhmarket.mobile.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.hhmarket.mobile.api.repository.ShoppingCartAPIRepository;
import com.hhmarket.mobile.di.Order;
import com.hhmarket.mobile.model.CartItem;
import com.hhmarket.mobile.model.CartItemDetail;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingCartModel extends AndroidViewModel {

    @Inject
    public ShoppingCartAPIRepository mShoppingCartAPIRepository;

    private MutableLiveData<CartItem> mObserverCartItemMutableLiveData;
    private MutableLiveData<List<CartItemDetail>> responeCartItem;
    private MediatorLiveData<List<CartItemDetail>> mObserverCartListMutableLiveData;
    private MutableLiveData<Order> orderMutableLiveData;
    private MutableLiveData<CartItem> updateCardItemMutableLiveData;
    private MutableLiveData<Integer> removeCardItemMutableLiveData;
    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<Throwable> apiError;


    private int mUserId;
    private final String TAG = "ShoppingCartModel";

    public ShoppingCartModel(@NonNull Application application, int userId) {
        super(application);

        // initial mutable
        mUserId = userId;
        mObserverCartItemMutableLiveData = new MutableLiveData<>();
        mObserverCartListMutableLiveData = new MediatorLiveData<>();
        responeCartItem = new MutableLiveData<>();
        orderMutableLiveData = new MutableLiveData<>();
        updateCardItemMutableLiveData = new MutableLiveData<>();
        removeCardItemMutableLiveData = new MutableLiveData<>();
        isLoading = new MediatorLiveData<>();
        apiError  = new MediatorLiveData<>();

        mObserverCartListMutableLiveData.setValue(null);

        mObserverCartListMutableLiveData.addSource(responeCartItem, mObserverCartListMutableLiveData::setValue);

    }

    public void getShoppingCartItemListFromAPI(){

        Callback<List<CartItemDetail>> callback = new Callback<List<CartItemDetail>>() {
            @Override
            public void onResponse(Call<List<CartItemDetail>> call, Response<List<CartItemDetail>> response) {

                if(response.isSuccessful()) {
                    responeCartItem.postValue(response.body());
                } else {
                    Log.i(TAG, "can not get data from API");
                    responeCartItem.postValue(null);
                }
                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<List<CartItemDetail>> call, Throwable t) {

                apiError.postValue(t);
                isLoading.postValue(false);
                responeCartItem.postValue(null);

            }
        };

        mShoppingCartAPIRepository.getShoppingCartList(mUserId, callback);
    }

    public void updateShoppingCartItemFromAPI(int cartDetailId, int amount) {

        Callback<CartItem> callback = new Callback<CartItem>() {
            @Override
            public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                if(response.isSuccessful()) {
                    updateCardItemMutableLiveData.postValue(response.body());
                } else {
                    updateCardItemMutableLiveData.postValue(null);
                }
                    isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<CartItem> call, Throwable t) {

                apiError.postValue(t);
                isLoading.postValue(false);
                updateCardItemMutableLiveData.postValue(null);

            }
        };

        mShoppingCartAPIRepository.updateShoppingCartItem(cartDetailId,amount, callback);

    }
    public void removeShoppingCartItemFromAPI(int cartDetailId) {

        Callback<Integer> callback = new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                System.out.println("response.isSuccessful() -> " + response.isSuccessful());
                System.out.println("response.body -> " + response.body());
                if(response.isSuccessful()) {
                    removeCardItemMutableLiveData.postValue(response.body());
                } else {
                    removeCardItemMutableLiveData.postValue(null);
                }
                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

                System.out.println("onFailure");
                apiError.postValue(t);
                isLoading.postValue(false);
                removeCardItemMutableLiveData.postValue(null);

            }
        };

        mShoppingCartAPIRepository.removeShoppingCartItem(cartDetailId, callback);

    }

    public void addShoppingCartItemFromAPI(String productDetailsId, int amount, float extendedPrice, int type) {

        Callback<CartItem> callback = new Callback<CartItem>() {
            @Override
            public void onResponse(Call<CartItem> call, Response<CartItem> response) {

                if (response.isSuccessful()){
                    mObserverCartItemMutableLiveData.postValue(response.body());
                }

                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<CartItem> call, Throwable t) {

                isLoading.postValue(false);
                apiError.postValue(t);
            }
        };

        mShoppingCartAPIRepository.addShoppingCartItem(mUserId,productDetailsId, amount, extendedPrice, type, callback);
    }

    public void orderCartItemFromAPI() {
        Callback<Order> callback = new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {

                if(response.isSuccessful()) {
                    orderMutableLiveData.postValue(response.body());
                }
                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

                isLoading.postValue(false);
                apiError.postValue(t);
            }
        };

        mShoppingCartAPIRepository.orderList(mUserId, callback);
    }

    public LiveData<List<CartItemDetail>> getShoppingCartItemList() {
        return mObserverCartListMutableLiveData;
    }

    public LiveData<CartItem> addShoppingCartItem() {
        return mObserverCartItemMutableLiveData;
    }

    public LiveData<CartItem> updateQuantityShoppingCart() {
        return updateCardItemMutableLiveData;
    }
    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public LiveData<Order> orderCartItem() {
        return orderMutableLiveData;
    }

    public LiveData<Integer> removeCardItem() {return  removeCardItemMutableLiveData;}


}
