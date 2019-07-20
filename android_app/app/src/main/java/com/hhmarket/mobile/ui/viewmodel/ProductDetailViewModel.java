package com.hhmarket.mobile.ui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.hhmarket.mobile.api.repository.ProductionAPIRepository;
import com.hhmarket.mobile.model.ProductDetail;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailViewModel extends AndroidViewModel{
    @Inject
    public ProductionAPIRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<ProductDetail>> mObservableProductDetail = new MediatorLiveData<>();

    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<Throwable> apiError;
    private MutableLiveData<List<ProductDetail>> productDetail;

    private String mProductId;

    public ProductDetailViewModel(Application application, String _productId) {
        super(application);

        /* LiveData -> MutableLiveData -> MediatorLiveData */
       // mObservableProductDetail = new MediatorLiveData<>();

        isLoading = new MediatorLiveData<>();
        apiError  = new MediatorLiveData<>();
        productDetail =  new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableProductDetail.setValue(null);

        // observe the changes of the products from the database and forward them
        mObservableProductDetail.addSource(productDetail, mObservableProductDetail::setValue);

        mProductId = _productId;
    }

    public void getProductDetailfromAPI() {
        Callback<List<ProductDetail>> callback = new Callback<List<ProductDetail>>() {
            @Override
            public void onResponse(Call<List<ProductDetail>> call, Response<List<ProductDetail>> response) {
                Log.i("-------onResponse--------", response.body().toString());
                productDetail.postValue(response.body());
                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<List<ProductDetail>> call, Throwable t) {
                Log.i("-------onFailure--------", "-----------------------------");
                apiError.postValue(t);
                isLoading.postValue(false);

            }

        };
        mRepository.getProductDetail(mProductId,callback);

    }

    /**
     * Expose the LiveData Product Detail so the UI can observe it.
     */
    public LiveData<List<ProductDetail>> getProductDetail() {
        return mObservableProductDetail;
    }

    public LiveData<Boolean> getIsLoading() {
        return this.isLoading;
    }
}
