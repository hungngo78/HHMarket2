package com.hhmarket.mobile.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.hhmarket.mobile.api.repository.ProductionAPIRepository;
import com.hhmarket.mobile.model.Product;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListViewModel extends AndroidViewModel {
    @Inject
    public ProductionAPIRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Product>> mObservableProducts;

    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<Throwable> apiError;
    private MutableLiveData<List<Product>> productsResponse;

    private String mCategoryId;
    private String mCriteria;

    private Callback<List<Product>> mCallback = new Callback<List<Product>>() {
        @Override
        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
            //Log.i("-------onResponse--------", response.body().toString());
            productsResponse.postValue(response.body());
            isLoading.postValue(false);
        }

        @Override
        public void onFailure(Call<List<Product>> call, Throwable t) {
            //Log.i("-------onFailure--------", "-----------------------------");
            apiError.postValue(t);
            isLoading.postValue(false);
        }
    };

    public ProductListViewModel(Application application, String _categoryId, String _criteria) {
        super(application);

        /* LiveData -> MutableLiveData -> MediatorLiveData */
        mObservableProducts = new MediatorLiveData<>();

        productsResponse =  new MediatorLiveData<>();
        isLoading = new MediatorLiveData<>();
        apiError  = new MediatorLiveData<>();

        // set by default null, until we get data from the database.
        mObservableProducts.setValue(null);

        // observe the changes of the products from the database and forward them
        mObservableProducts.addSource(productsResponse, mObservableProducts::setValue);

        mCategoryId = _categoryId;
        mCriteria = _criteria;
    }

    public void getProductsfromAPI() {
        mRepository.getProducts(mCategoryId, mCallback);
    }

    public void searchProductsfromAPI() {
        mRepository.searchProducts(mCriteria, mCallback);
    }

    /**
     * Expose the LiveData ProductList so the UI can observe it.
     */
    public LiveData<List<Product>> getProducts() {
        return mObservableProducts;
    }

    public LiveData<Boolean> getIsLoading() {
        return this.isLoading;
    }



}
